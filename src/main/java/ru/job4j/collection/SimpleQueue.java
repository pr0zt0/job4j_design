package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    private void regroupData(SimpleStack<T> f, SimpleStack<T> d) {
        int temp = size;
        while (temp != 0) {
            d.push(f.pop());
            temp--;
        }
    }

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        regroupData(in, out);
        size--;
        T data = out.pop();
        if (size != 0) {
            regroupData(out, in);
        }
        return data;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}