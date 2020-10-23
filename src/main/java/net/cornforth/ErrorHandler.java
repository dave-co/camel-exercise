package net.cornforth;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.commons.text.StringEscapeUtils;

@Slf4j
public class ErrorHandler {

    public void handleError(Exchange exchange) {
        Exception caughtException = exchange.getProperty("CamelExceptionCaught", Exception.class);
        log.error("Exception caught", caughtException);
        String message = caughtException == null ? "no exception" : caughtException.getMessage();
        message = StringEscapeUtils.escapeXml11(message);
        exchange.getIn().setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<response>"+
                "\t<result>failed</result>\n" +
                "\t<message>" + message + "</message>" +
                "</response>");
    }
}
