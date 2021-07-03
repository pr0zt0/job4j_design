package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> nodeLast = null;
    private Node<E> nodeFirst = null;
    private int size = 0;
    private int modCount = 0;

    private boolean isEmpty() {
        return nodeFirst == null;
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (isEmpty()) {
            nodeFirst = newNode;
        } else {
            nodeLast.nodeNext = newNode;
        }
        nodeLast = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = nodeFirst;
        for (int i = 0; i < index; i++) {
            node = node.nodeNext;
        }
        return node.getData();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> curr = nodeFirst;
            private int exModCount = modCount;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (exModCount != modCount) {
                    throw new ConcurrentModificationException("mod array!");
                }
                E data = curr.getData();
                curr = curr.nodeNext;
                return data;
            }
        };
    }
}