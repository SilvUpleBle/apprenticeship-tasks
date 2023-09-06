package org.task_3;

import org.example.jaxb.esia.ESIAFindAccountRequest;
import org.task_3.resources.Converter;

public class Main {
    public static void main(String[] args) {
        try {
            Converter.xmlToJson("src/main/resources/esia_xml/request.xml", ESIAFindAccountRequest.class, "output.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}