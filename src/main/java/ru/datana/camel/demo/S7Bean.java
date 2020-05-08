package ru.datana.camel.demo;

import org.apache.camel.Body;
import org.apache.camel.Handler;

public class S7Bean {

    @Handler
    public String doMyLogic(@Body String body) {
        return "My Logic got " + body;
    }

    public String setMyHeader() {
        return "Here's my header definition, whatever the logic is";
    }

}