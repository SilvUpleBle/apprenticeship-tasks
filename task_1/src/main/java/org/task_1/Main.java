package org.task_1;

import org.task_1.resources.List;
import org.task_1.resources.SomeData2;


public class Main {
    public static void main(String[] args) {

        List<SomeData2> list2 = new List<>();
        list2.add(new SomeData2());
        list2.add(new SomeData2());
        list2.add(new SomeData2());
        System.out.println(list2);

        try {
            list2.saveToFile("C:\\Users\\Andrei\\Desktop\\output2.json");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<List<SomeData2>> list3 = new List<>();
        list3.add(list2);
        list3.add(list2);
        list3.add(list2);
        System.out.println(list3);

        try {
            list3.saveToFile("C:\\Users\\Andrei\\Desktop\\output3.json");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}