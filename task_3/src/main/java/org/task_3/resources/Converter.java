package org.task_3.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class Converter {

    private Converter() {}

    public static <T> T xmlToPojo(String xmlPath, Class<T> classType) throws IOException, JAXBException {
        String xmlString = Files.readString(Paths.get(xmlPath), StandardCharsets.UTF_8);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return unmarshaller.unmarshal(new StreamSource(new StringReader(xmlString)), classType).getValue();
        } catch (UnmarshalException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException("Could not unmarshall object from xml!");
        }
    }

    public static <T> void pojoToJson(T obj, String jsonPath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(jsonPath), obj);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static <T> void xmlToJson(String xmlPath, Class<T> classType, String jsonPath) throws IOException, JAXBException  {
        T obj = xmlToPojo(xmlPath, classType);
        pojoToJson(obj, jsonPath);
        log.info("%s from xml successfully converted to json on path \"%s!\"".formatted(classType, new File(jsonPath).getAbsolutePath()));
    }
}
