package org.task_2.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.task_2.resources.abstracts.TreeNode;

import java.io.FileReader;
import java.util.LinkedList;

@Slf4j
@NoArgsConstructor
public class BinaryTree {
    private Node root;
    private int size = 0;

    public void add(TreeNode object) {
        if (root == null)
            root = new Node(object);
        else {
            Node parent = getParentNode(new Node(object));
            // проверка на то, существует ли уже нода с такими данными
            if (parent == null ||
                    parent.left != null && parent.left.data.equals(object) ||
                    parent.right != null && parent.right.data.equals(object))
                return;

            if (object.compareTo(parent.data) > 0)
                parent.right = new Node(object);
            else
                parent.left = new Node(object);
        }
        size++;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return size;
    }

    public boolean contains(Long id, Long subId) {
        return find(id, subId) != null;
    }

    public boolean contains(TreeNode object) {
        if (find(object.getId(), object.getSubId()) == null)
            return false;
        return find(object.getId(), object.getSubId()).equals(object);
    }

    public TreeNode first() {
        return root.data;
    }

    public boolean remove(Long id, Long subId) {
        if (!contains(id, subId))
            return false;
        if (root.data.getId() == id && root.data.getSubId() == subId)
            return removeRootNode();
        else
            return removeNonRootNode(findNode(id, subId));
    }

    public boolean remove(TreeNode object) {
        if (!contains(object))
            return false;
        if (object.equals(root.data))
            return removeRootNode();
        else
            return removeNonRootNode(findNode(object.getId(), object.getSubId()));
    }

    public TreeNode find(Long id, Long subId) {
        if (findNode(id, subId) != null)
            return findNode(id, subId).data;
        return null;
    }

    // возвращает ноду, если она есть
    // возвращает null, если ноды нет
    private Node findNode(Long id, Long subId) {
        Node current = root;
        while (current != null) {
            if (id + subId == current.data.getId() + current.data.getSubId())
                return current;
            else {
                if (id + subId > current.data.getId() + current.data.getSubId())
                    current = current.right;
                else
                    current = current.left;
            }
        }
        return null;
    }

    // TODO проверить
    public void addFromJson(String src) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodes = mapper.readTree(new FileReader(src));
            for (JsonNode node : jsonNodes) {
                try {
                    add(mapper.treeToValue(node, TreeNode.class));
                } catch (Exception e) {
                    log.error("The element \"%s\" was not added to the tree because it is not an inheritor of the TreeNode".formatted(node.toString()));
                }
            }
        } catch (Exception e) {
            log.info("File on path \"%s\" was not found!".formatted(src));
        }
    }

    public TreeNode[] toArray(TreeNode[] mas) {
        LinkedList<TreeNode> list = new LinkedList<>();
        lcr(root, list);
        return list.toArray(mas);
    }

    private void lcr(Node node, LinkedList<TreeNode> list) {
        if (node != null) {
            lcr(node.left, list);
            list.add(node.data);
            lcr(node.right, list);
        }
    }

    private boolean removeRootNode() {
        if (isEmpty())
            return false;
        if (size == 1)
            root = null;
        else {
            if (root.left != null && root.right != null) {
                Node tmp = root.left;
                while (tmp.right != null)
                    tmp = tmp.right;
                tmp.right = root.right;
                root = tmp;
            } else {
                if (root.left != null)
                    root = root.left;
                else
                    root = root.right;
            }
        }
        size--;
        return true;
    }

    private boolean removeNonRootNode(Node heir) {
        if (isEmpty() || heir == null)
            return false;

        Node parent = getParentNode(heir);
        // если у ноды нет наследников слева и справа, то это значение не изменится
        Node tmp = null;
        if (heir.left != null || heir.right != null) {
            // если у ноды есть только левый наследник
            if (heir.right == null)
                tmp = heir.left;
            // если у ноды есть только правый наследник
            if (heir.left == null)
                tmp = heir.right;
            // если есть левый и правый наследники
            if (heir.left != null && heir.right != null) {
                tmp = heir.left;
                while (tmp.right != null)
                    tmp = tmp.right;
                tmp.right = heir.right;
            }
        }

        if (heir.equals(parent.left)) {
            parent.left = tmp;
        } else {
            parent.right = tmp;
        }

        size--;
        return true;
    }

    // возвращает родителя ноды
    // возвращает null, если нода с такими данными уже есть
    private Node getParentNode(Node heir) {
        if (heir.data == root.data)
            return null;

        Node current = root;
        Node prev = null;
        while (current != null) { // && heir.data.compareTo(current.data) != 0
            prev = current;
            if ((current.left != null && heir.data.equals(current.left.data)) || (current.right != null && heir.data.equals(current.right.data)))
                break;
            if (heir.data.compareTo(current.data) > 0)
                current = current.right;
            else
                current = current.left;
        }

        return prev;
    }

    @Getter
    private class Node {
        private final TreeNode data;
        private Node left = null;
        private Node right = null;

        Node(TreeNode object) {
            data = object;
        }
    }
}
