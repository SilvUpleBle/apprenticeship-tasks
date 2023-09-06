package org.task_1.resources;

import lombok.Cleanup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.task_1.resources.abstracts.DataNode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.NoSuchElementException;

@NoArgsConstructor
@Getter
public class List<T extends DataNode> extends DataNode {
    private Node first;
    private Node last;
    private int size = 0;

    public void add(T data) {
        last = new Node(data);
        if (size == 0)
            first = last;
        else
            getNode(size - 1).next = last;
        size++;
    }

    public void add(int index, T data) {
        if (index != 0)
            checkIndex(index);
        Node node = new Node(data);

        if (index != size - 1 && index != 0) {
            node.next = getNode(index - 1).next;
            getNode(index - 1).next = node;
        } else {
            if (index == 0) {
                node.next = first;
                first = node;
                if (size == 0)
                    last = first;
            } else {
                node.next = last;
                getNode(index - 1).next = node;
            }
        }
        size++;
    }

    public void remove(int index) {
        checkIndex(index);

        if (size == 1)
            clear();

        if (index != size - 1 && index != 0)
            getNode(index - 1).next = getNode(index).next;
        else {
            if (index == 0)
                first = first.next;
            else
                getNode(index - 1).next = null;
        }
        size--;
    }

    public void remove() {
        remove(size - 1);
    }

    public void remove(T object) {
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.data == object) {
                remove(i);
                return;
            }
            else
                current = current.next;
        }

        throw new NoSuchElementException();
    }

    public T get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    public void set(int index, T newData) {
        checkIndex(index);
        getNode(index).data = newData;
    }

    public int indexOf(T object) {
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(object))
                return i;
            current = current.next;
        }
        throw new NoSuchElementException();
    }

    public T[] toArray(T[] array) {
        if (array.length != size)
            throw new IndexOutOfBoundsException();

        Node current = first;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }

    public int lastIndexOf(T object) {
        Node current = first;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(object))
                index = i;
            current = current.next;
        }

        if (index == -1)
            throw new NoSuchElementException();
        else
            return index;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder().append('[');
        for (Node current = first; current != null; current = current.next) {
            sb.append(current.data).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()).append(']');
        return sb.toString();
    }

    public void saveToFile(String path) throws IOException {
        checkFileFormat(path);

        @Cleanup FileWriter file = new FileWriter(path);
        file.write(this.print());
    }

    private void checkIndex(int index) {
        if (index > size - 1 || index < 0)
            throw new IndexOutOfBoundsException();
    }

    private Node getNode(int index) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkFileFormat(String path) throws InvalidPropertiesFormatException {
        if (!path.endsWith("json"))
            throw new InvalidPropertiesFormatException("File must have an extension .json!");
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("[\n");
        for (Node current = first; current != null; current = current.next)
            sb.append((current.data.print() + ((current != last) ? ",\n" : "\n") ).indent(4));
        sb.append("]");
        return sb.toString();
    }

    @Getter
    private class Node {
        public T data;
        public Node next = null;
        public Node(T data) { this.data = data; }

    }

}
