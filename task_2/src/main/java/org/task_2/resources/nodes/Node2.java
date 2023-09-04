package org.task_2.resources.nodes;

import lombok.NoArgsConstructor;
import org.task_2.resources.abstracts.TreeNode;

@NoArgsConstructor
public class Node2 extends TreeNode {
    public Node2(Long id, Long subId) {
        this.setId(id);
        this.setSubId(subId);
    }
}
