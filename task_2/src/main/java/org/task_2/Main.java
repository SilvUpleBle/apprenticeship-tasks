package org.task_2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.task_2.resources.abstracts.TreeNode;
import org.task_2.resources.nodes.Node1;
import org.task_2.resources.nodes.Node2;

import java.io.File;
import java.io.FileReader;

public class Main {
    final static String src = "C:\\Users\\Andrei\\Desktop\\output2.json";

    // TODO не должны выбрасываться исключения, но должны логироваться

    public static void main(String[] args) {
        try {
            TreeNode tr = new Node1(1, "1", 1.1, true);
            System.out.println(" ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}