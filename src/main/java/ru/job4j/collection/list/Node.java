package ru.job4j.collection.list;

public class Node<E> {
    private E data;
    public Node<E> nodeNext;

    public Node(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }
}
