package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private T[] container = (T[]) new Object[]{};
    private int size = 0;
    private int modCount = 0;

    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    public void add(T model) {
        if (container.length == size) {
            container = Arrays.copyOf(container, (size + 1) * 2);
        }
        container[size++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final T[] data = container;
            private int point = 0;
            private int exModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (exModCount != modCount) {
                    throw new ConcurrentModificationException("mod array!");
                }
                return data[point++];
            }
        };
    }
}