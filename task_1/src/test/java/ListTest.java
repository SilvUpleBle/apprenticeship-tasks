import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.task_1.resources.List;
import org.task_1.resources.SomeData1;
import org.task_1.resources.SomeData2;
import org.task_1.resources.SomeData3;
import org.task_1.resources.abstracts.DataNode;

import java.util.LinkedList;

public class ListTest {
    @Test
    public void addTest() {
        List<DataNode> list = new List<DataNode>();
        list.add(new SomeData1());
        list.add(new SomeData2());
        list.add(new SomeData3());

        java.util.List<DataNode> listOrig = new LinkedList<>();
        listOrig.add(new SomeData1());
        listOrig.add(new SomeData2());
        listOrig.add(new SomeData3());

        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(listOrig.get(i).print(), list.get(i).print());
        }

        list.add(1, new SomeData1());
        listOrig.add(1, new SomeData1());

        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(listOrig.get(i).print(), list.get(i).print());
        }
    }

    @Test
    public void removeTest() {
        List<DataNode> list = new List<DataNode>();
        list.add(new SomeData1());
        list.add(new SomeData2());
        list.add(new SomeData3());
        list.remove(1);

        java.util.List<DataNode> listOrig = new LinkedList<>();
        listOrig.add(new SomeData1());
        listOrig.add(new SomeData2());
        listOrig.add(new SomeData3());
        listOrig.remove(1);

        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(listOrig.get(i).print(), list.get(i).print());
        }
    }

    @Test
    public void setTest() {
        List<DataNode> list = new List<DataNode>();
        list.add(new SomeData1());
        list.add(new SomeData2());
        list.add(new SomeData3());
        list.set(1, new SomeData1());

        java.util.List<DataNode> listOrig = new LinkedList<>();
        listOrig.add(new SomeData1());
        listOrig.add(new SomeData2());
        listOrig.add(new SomeData3());
        listOrig.set(1, new SomeData1());

        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(listOrig.get(i).print(), list.get(i).print());
        }
    }

    @Test
    public void indexOfTest() {
        SomeData2 sd2 = new SomeData2();

        List<DataNode> list = new List<DataNode>();
        list.add(new SomeData1());
        list.add(sd2);
        list.add(new SomeData3());

        java.util.List<DataNode> listOrig = new LinkedList<>();
        listOrig.add(new SomeData1());
        listOrig.add(sd2);
        listOrig.add(new SomeData3());

        Assertions.assertEquals(listOrig.indexOf(sd2), list.indexOf(sd2));

    }

    @Test
    public void lastIndexOfTest() {
        SomeData2 sd2 = new SomeData2();

        List<DataNode> list = new List<DataNode>();
        list.add(sd2);
        list.add(sd2);
        list.add(sd2);

        Assertions.assertEquals(2, list.lastIndexOf(sd2));
    }

}
