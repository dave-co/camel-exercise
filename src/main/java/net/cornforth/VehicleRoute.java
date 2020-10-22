package net.cornforth;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.processor.validation.SchemaValidationException;
import org.springframework.stereotype.Component;

@Component
public class VehicleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");

        rest("/vehicle")
                .id("rest-vehicle")
                .post("/add")
                .to("direct:add");

        from("direct:add")
            .id("add-vehicle")
            .doTry()
                .convertBodyTo(java.lang.String.class, "UTF-8")
                .log("add")
                .to("validator:addVehicle.xsd")
            .doCatch(SchemaValidationException.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("400"))
                .log("${exception.message}")
            .doCatch(Exception.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500"))
                .log("${exception.message}")
            .end();
        ;
    }
}
