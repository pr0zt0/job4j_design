package ru.job4j.generics;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<T> list = Stream.of(arr).collect(Collectors.toList());
        return list.iterator();
    }
}
