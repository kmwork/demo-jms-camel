package ru.datana.camel.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.engine.SimpleCamelContext;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.datana.camel.demo.config.DatanaCamelConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Import(DatanaCamelConfig.class)
public class KostyaApplication implements CommandLineRunner {

    @Autowired
    private CamelContext camelContext;

    public static void main(String[] args)  throws Exception {
        SpringApplication.run(KostyaApplication.class, args);



    }

    public static String readFile() throws IOException {
        File file = new File("/home/lin/work-lanit/integration-adapter-rest-to-activemq/src/main/resources/request-example.json");
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    @Override
    public void run(String... args) throws Exception {


        String requestEndpointUri = "http://172.29.40.42:8080/rest/getData";
        Map<String, Object> requestHeaders = new HashMap<>();
        requestHeaders.put(Exchange.HTTP_METHOD, "POST");
        requestHeaders.put(Exchange.CONTENT_TYPE, "application/json");
        String reqestBody = readFile();

        ProducerTemplate template = camelContext.createProducerTemplate();
        //Get Json from REST and Send Json to JMS
        String responseBodyJsonFromRest = template.requestBodyAndHeaders(requestEndpointUri, reqestBody, requestHeaders, String.class);

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() throws Exception {
                from("timer://bar?fixedRate=true&delay=0&period=1000")
                        .setBody(constant(responseBodyJsonFromRest))
                        .log("[SEND] dimaBBBB")
                        .to("activemq:dimaBBB");
            }

        });
    }
}








