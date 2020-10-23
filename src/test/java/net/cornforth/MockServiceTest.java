package net.cornforth;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MockServiceTest {
    private MockService mockService;

    @BeforeEach
    public void before() {
        mockService = new MockService();
    }

    @Test
    public void doSomething() {
        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        mockService.doSomething(exchange);

        Message message = exchange.getIn();
        assertNotNull(message, "Message is null");
        Object body = message.getBody();
        assertNotNull(body, "Body is null");
        assertTrue(body instanceof String, "Body is not a string, it is a " + body.getClass());
        String bodyText = message.getBody(String.class);
        assertEquals(MockService.RESPONSE, bodyText, "bodyText is incorrect");
    }
}
