package net.cornforth;

import org.apache.camel.builder.RouteBuilder;
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
            .log("${body}");
    }
}
