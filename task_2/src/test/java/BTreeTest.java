import org.junit.jupiter.api.Test;
import org.task_2.resources.BinaryTree;
import org.task_2.resources.abstracts.TreeNode;
import org.task_2.resources.nodes.Node1;
import org.task_2.resources.nodes.Node2;
import org.task_2.resources.nodes.Node3;

public class BTreeTest {

    @Test
    public void test1() {
        Node1 n1 = new Node1();
        n1.setId(1L);
        n1.setSubId(1L);
        Node2 n2 = new Node2();
        n2.setId(2L);
        n2.setSubId(2L);
        Node3 n3 = new Node3();
        n3.setId(3L);
        n3.setSubId(3L);
        Node1 n11 = new Node1();
        n11.setId(-1L);
        n11.setSubId(-1L);
        Node2 n22 = new Node2();
        n22.setId(2L);
        n22.setSubId(2L);
        Node3 n33 = new Node3();
        n33.setId(4L);
        n33.setSubId(4L);

        BinaryTree<TreeNode> bTree = new BinaryTree<>();
        bTree.add(n2);
        bTree.add(n1);
        bTree.add(n3);
        bTree.add(n22);
        bTree.add(n11);
        bTree.add(n33);
        System.out.println("test1 end");
    }

}
