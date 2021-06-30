package ru.job4j.generics;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private T[] arr;
    private int size = 0;

    public SimpleArray(int size) {
        this.arr = (T[]) new Object[size];
    }

    public void add(T model) {
        Objects.checkIndex(size, arr.length);
        arr[size] = model;
        size++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        arr[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(arr, index + 1, arr, index, size);
        size--;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return arr[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final T[] data = arr;
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[point++];
            }
        };
    }
}