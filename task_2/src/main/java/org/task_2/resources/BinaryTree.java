package org.task_2.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.task_2.resources.abstracts.TreeNode;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@NoArgsConstructor
public class BinaryTree <T extends TreeNode> {
    private Node root;
    private int size = 0;

    public void add(T object) {
        if (root == null)
            root = new Node(object);
        else {
            Node parent = getParentNode(new Node(object));
            // проверка на то, существует ли уже нода с такими данными
            if (parent.left.data.equals(object) || parent.right.data.equals(object))
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

    public boolean contains(T object) {
        return find(object.getId(), object.getSubId()).equals(object);
    }

    public T first() {
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

    public boolean remove(T object) {
        if (!contains(object))
            return false;
        if (object.equals(root.data))
            return removeRootNode();
        else
            return removeNonRootNode(findNode(object.getId(), object.getSubId()));
    }

    public T find(Long id, Long subId) {
        return findNode(id, subId).data;
    }

    private Node findNode(Long id, Long subId) {
        Node current = root;
        while (current != null) {
            if (id + subId == current.data.getId() + current.data.getSubId())
                return current;
            else
            if (id + subId > current.data.getId() + current.data.getSubId())
                current = current.right;
            else
                current = current.left;
        }
        throw new NoSuchElementException("This element is missing in the tree!");
    }

    public void addFromJson(String src) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodes = mapper.readTree(new FileReader(src));
            for (JsonNode node: jsonNodes) {
                try {
                    //add(mapper.treeToValue(node, T));
                } catch (Exception e) {
                    // TODO выводить в лог
                }
            }
        } catch (Exception e) {
            
        }
    }

    public T[] toArray(T[] mas) {
        LinkedList<T> list = new LinkedList<>();
        lcr(root, list);
        return list.toArray(mas);
    }

    private void lcr(Node node, LinkedList<T> list) {
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
        if (isEmpty())
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

    // получает родителя ноды
    private Node getParentNode(Node heir) {
        if (heir == root)
            return null;

        Node current = root;
        Node prev = null;
        while (current != null || current.left.data.equals(heir.data) || current.right.data.equals(heir.data)) {
            prev = current;
            if (heir.data.compareTo(current.data) > 0)
                current = current.right;
            else
                current = current.left;
        }
        return prev;
    }

    @Getter
    private class Node {
        private final T data;
        private Node left = null;
        private Node right = null;

        Node(T object) {
            data = object;
        }
    }
}
