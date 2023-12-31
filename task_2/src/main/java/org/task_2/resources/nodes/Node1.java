package org.task_2.resources.nodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.task_2.resources.abstracts.TreeNode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Node1 extends TreeNode {
    private int paramInt;
    private String paramStr;
    private double paramDouble;
    private boolean paramBool;

    public Node1(Long id, Long subId) {
        this.setId(id);
        this.setSubId(subId);
    }
}
