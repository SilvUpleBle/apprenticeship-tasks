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
        StringBuilder sb = new StringBuilder("{\n\t");
        sb.append("\"paramInt\" : ").append(paramInt).append(",\n\t");
        sb.append("\"paramStr\" : ").append(paramStr).append(",\n\t");
        sb.append("\"paramDouble\" : ").append(paramDouble).append(",\n\t");
        sb.append("\"paramBool\" : ").append(paramBool).append("\n}");
        return sb.toString();
    }
}
