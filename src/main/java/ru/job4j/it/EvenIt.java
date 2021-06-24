package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    private int index = 0;

    public EvenIt(int[] numbers) {
        this.data = numbers;
    }

    private int getIndex() {
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        if (getIndex() >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = getIndex();
        return data[index++];
    }

    public static void main(String[] args) {
        Iterator it = new EvenIt(new int[] {4, 2, 3, 1});
        System.out.println(it.next() + " " + it.next());
        System.out.println(it.hasNext());
    }
}
