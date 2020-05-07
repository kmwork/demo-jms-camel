package ru.datana.camel.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.HashMap;
import java.util.Map;

public class DemoKosyaCamelApp {

    public static void main(String... args) {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.start();

        ProducerTemplate template = camelContext.createProducerTemplate();

        Map<String, Object> requestHeaders = new HashMap<>();
        String requestEndpointUri = "http://www.mocky.io/v2/5185415ba171ea3a00704eed ";
        String reqestBody="";
        String body = template.requestBodyAndHeaders(requestEndpointUri, reqestBody, requestHeaders, String.class);


        System.out.println("[Kostya] body = "+body);
    }

}