package net.cornforth;

import org.apache.camel.Exchange;

public class MockService {

    static final String RESPONSE = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<response>"+
            "\t<result>success</result>"+
            "</response>";

    public void doSomething(Exchange exchange){
        exchange.getIn().setBody(RESPONSE);
    }
}
