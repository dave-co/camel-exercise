package net.cornforth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleRouteTest {

    private static final String VALID_REQUEST = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<addVehicle>\n" +
            "  <assetId>1</assetId>\n" +
            "  <make>ford</make>\n" +
            "  <model>mondeo</model>\n" +
            "</addVehicle>";

    private static final String INVALID_REQUEST = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<addVehicle>\n" +
            "  <assetId>AAAAAAA</assetId>\n" + // assetId should be an integer in a valid request
            "  <make>ford</make>\n" +
            "  <model>mondeo</model>\n" +
            "</addVehicle>";

    private static final String SUCCESS_RESPONSE = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<response>" +
            "\t<result>success</result>"+
            "</response>";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addVehicle_success() {
        ResponseEntity<String> response = restTemplate.postForEntity("/camel/vehicle/add", VALID_REQUEST, String.class);
        assertEquals("HTTP status code incorrect", HttpStatus.OK, response.getStatusCode());
        String body = response.getBody();
        assertNotNull("response body is null", body);
        assertEquals("response is incorrect", SUCCESS_RESPONSE, body);
    }

    @Test
    public void addVehicle_failure() {
        ResponseEntity<String> response = restTemplate.postForEntity("/camel/vehicle/add", INVALID_REQUEST, String.class);
        assertEquals("HTTP status code incorrect", HttpStatus.BAD_REQUEST, response.getStatusCode());
        String body = response.getBody();
        assertNotNull("response body is null", body);
        assertTrue("response is incorrect", body.contains("<result>failed</result>"));
    }
}
