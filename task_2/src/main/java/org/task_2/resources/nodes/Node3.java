package org.task_2.resources.nodes;

import lombok.NoArgsConstructor;
import org.task_2.resources.abstracts.TreeNode;

@NoArgsConstructor
public class Node3 extends TreeNode {
    public Node3(Long id, Long subId) {
        this.setId(id);
        this.setSubId(subId);
    }
}
