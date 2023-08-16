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
public class SomeData3 extends DataNode {
    private int paramInt1 = 1;
    private int paramInt2 = 2;
    private int paramInt3 = 3;

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
