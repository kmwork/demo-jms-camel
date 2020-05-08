package ru.datana.camel.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class DemoKosyaCamelApp {

    public static void main(String... args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.start();

        ProducerTemplate template = camelContext.createProducerTemplate();

        Map<String, Object> requestHeaders = new HashMap<>();
        requestHeaders.put(Exchange.HTTP_METHOD, "GET");
        String requestEndpointUri = "http://httpbin.org/get";
                //"https://jsonplaceholder.typicode.com/posts/42";
                // "https://my-json-server.typicode.com/typicode/demo/db";
                // "http://httpbin.org/get";
                // "http://www.mocky.io/v2/5185415ba171ea3a00704eed ";
        String reqestBody="";
        CompletableFuture<String> asycBody = template.asyncRequestBodyAndHeaders(requestEndpointUri, reqestBody, requestHeaders, String.class);

        while (!asycBody.isDone()) Thread.sleep(300);

        System.out.println("[Kostya] body = "+asycBody.get());
    }

}