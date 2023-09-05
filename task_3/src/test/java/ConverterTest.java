import lombok.extern.slf4j.Slf4j;
import org.example.jaxb.esia.ESIAFindAccountRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.task_3.resources.Converter;

import java.io.FileInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class ConverterTest {

    @Test
    public void xmlToPojoTest () {
        try {
            ESIAFindAccountRequest obj = Converter.xmlToPojo("src/main/resources/esia_xml/request.xml", ESIAFindAccountRequest.class);
            Assertions.assertEquals("Никита", obj.getFirstName());
            Assertions.assertEquals("Осипов", obj.getLastName());
            Assertions.assertEquals("995-277-326 80", obj.getSnils());
            Assertions.assertEquals("+7(961)6930604", obj.getMobile());
            Assertions.assertEquals("nik.osipov2011@yandex.ru", obj.getEmail());
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
    }
    // "src/main/resources/esia_xml/request.xml", ESIAFindAccountRequest.class, "output.json"
    @Test
    public void pojoToJsonTest() {
        try {
            ESIAFindAccountRequest obj = Converter.xmlToPojo("src/main/resources/esia_xml/request.xml", ESIAFindAccountRequest.class);
            Converter.pojoToJson(obj, "testOutput.json");
            Assertions.assertEquals(Files.readString(Paths.get("output.json"), StandardCharsets.UTF_8), Files.readString(Paths.get("testOutput.json"), StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void xmlToJsonTest() {
        try {
            Converter.xmlToJson("src/main/resources/esia_xml/request.xml", ESIAFindAccountRequest.class, "testOutput.json");
            Assertions.assertEquals(Files.readString(Paths.get("output.json"), StandardCharsets.UTF_8), Files.readString(Paths.get("testOutput.json"), StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
    }
}
