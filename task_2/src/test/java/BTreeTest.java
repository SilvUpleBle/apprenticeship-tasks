import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.task_2.resources.BinaryTree;
import org.task_2.resources.abstracts.TreeNode;
import org.task_2.resources.nodes.Node1;
import org.task_2.resources.nodes.Node2;
import org.task_2.resources.nodes.Node3;

import java.util.*;

public class BTreeTest {

    Node1 n1 = new Node1(1L, 1L);
    Node2 n2 = new Node2(2L, 2L);
    Node3 n3 = new Node3(3L, 3L);
    Node1 n11 = new Node1(-1L, -1L);
    Node2 n22 = new Node2(-2L, -2L);
    Node3 n33 = new Node3(4L, 4L);

    private BinaryTree fillBTree() {
        BinaryTree bTree = new BinaryTree();
        bTree.add(n2);
        bTree.add(n1);
        bTree.add(n3);
        bTree.add(n22);
        bTree.add(n11);
        bTree.add(n33);
        return bTree;
    }

    private List<TreeNode> fillList() {
        List<TreeNode> list = new ArrayList<>();
        list.add(n2);
        list.add(n1);
        list.add(n3);
        list.add(n22);
        list.add(n11);
        list.add(n33);
        return list;
    }

    @Test
    public void addTest() {
        BinaryTree bTree = fillBTree();

        List<TreeNode> list = fillList();
        Collections.sort(list);

        TreeNode[] mas1 = new TreeNode[bTree.size()];
        TreeNode[] mas2 = new TreeNode[bTree.size()];

        bTree.toArray(mas1);
        list.toArray(mas2);

        Assertions.assertArrayEquals(mas1, mas2);

        bTree.add(n2); // существующий элемент
        bTree.add(n22); // существующий элемент
        Assertions.assertArrayEquals(mas1, mas2);
    }

    @Test
    public void removeTest() {
        BinaryTree bTree = fillBTree();
        bTree.remove(n22);
        bTree.remove(n2);
        bTree.remove(-1L, -1L);
        bTree.remove(3L, 3L);

        List<TreeNode> list = fillList();
        list.remove(n22);
        list.remove(n2);
        list.remove(n11);
        list.remove(n3);
        Collections.sort(list);

        TreeNode[] mas1 = new TreeNode[bTree.size()];
        TreeNode[] mas2 = new TreeNode[bTree.size()];

        bTree.toArray(mas1);
        list.toArray(mas2);

        Assertions.assertArrayEquals(mas1, mas2);

        bTree.remove(new Node1(5L, 5L));    //несуществующий элемент
        bTree.remove(new Node1(10L, -10L)); //несуществующий элемент
        Assertions.assertArrayEquals(mas1, mas2);
    }

    @Test
    public void containsTest() {
        BinaryTree bTree = fillBTree();
        Assertions.assertEquals(true, bTree.contains(n2));
        Assertions.assertEquals(true, bTree.contains(n22));
        Assertions.assertEquals(true, bTree.contains(1L, 1L));
        Assertions.assertEquals(true, bTree.contains(4L, 4L));
        Assertions.assertEquals(true, bTree.contains(-4L, 2L));

        Assertions.assertEquals(false, bTree.contains(new Node1(5L, 5L)));
        Assertions.assertEquals(false, bTree.contains(new Node1(10L, -10L)));
        Assertions.assertEquals(false, bTree.contains(12L, -1L));
        Assertions.assertEquals(false, bTree.contains(-4L, 3L));
    }

    @Test
    public void findTest() {
        BinaryTree bTree = fillBTree();
        Assertions.assertEquals(n2, bTree.find(2L, 2L));
        Assertions.assertEquals(n11, bTree.find(-1L, -1L));
        Assertions.assertEquals(n3, bTree.find(3L, 3L));

        Assertions.assertEquals(n2, bTree.find(1L, 3L));
        Assertions.assertEquals(null, bTree.find(34L, 31L));
    }

    @Test
    public void addFromJsonTest() {
        BinaryTree bTree = new BinaryTree();
        bTree.addFromJson("input.json");

        List<TreeNode> list = fillList();
        Collections.sort(list);

        TreeNode[] mas1 = new TreeNode[bTree.size()];
        TreeNode[] mas2 = new TreeNode[list.size()];

        bTree.toArray(mas1);
        list.toArray(mas2);

        for (int i = 0; i < bTree.size(); i++) {
            Assertions.assertEquals(mas1[i].toString(), mas2[i].toString());
        }
    }
}
