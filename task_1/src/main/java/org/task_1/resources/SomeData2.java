package org.task_1.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.task_1.resources.abstracts.DataNode;

@Getter
@Setter
public class SomeData2 extends DataNode {
    private int paramInt = 2;
    private String paramStr = "some data 2";
    private double paramDouble = 0.99999999;
    private boolean paramBool = false;

    @Override
    public String print() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DefaultPrettyPrinter printer = new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("    ", "\n"));
            return objectMapper.writer(printer).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }
}
