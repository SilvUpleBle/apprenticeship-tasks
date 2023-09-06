package org.task_2.resources.abstracts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.task_2.resources.nodes.Node1;
import org.task_2.resources.nodes.Node2;
import org.task_2.resources.nodes.Node3;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "className")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Node1.class, name = "Node1"),
        @JsonSubTypes.Type(value = Node2.class, name = "Node2"),
        @JsonSubTypes.Type(value = Node3.class, name = "Node3")
})
public abstract class TreeNode implements Comparable<TreeNode> {

    @NonNull
    private Long id;
    @NonNull
    private Long subId;
    private String className;

    @Override
    public int compareTo(TreeNode o) {
        return Long.compare(this.id + this.subId, o.id + o.subId);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", subId=" + subId +
                ", className='" + className + '\'' +
                '}';
    }
}
