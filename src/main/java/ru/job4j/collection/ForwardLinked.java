package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        node.next = head;
        head = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = head;
        head = head.next;
        return node.value;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> curr = head;
        Node<T> pr = null;
        Node<T> future;
        while (curr != null) {
            future = curr.next;
            curr.next = pr;
            pr = curr;
            curr = future;
        }
        head = pr;
        return true;
    }

    public T deleteLast() {
        Node<T> node = head;
        Node<T> pr = head;
        while (node.next != null) {
            pr = node;
            node = node.next;
        }
        if (node == head) {
            head = null;
        } else {
            pr.next = null;
        }
        return node.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}