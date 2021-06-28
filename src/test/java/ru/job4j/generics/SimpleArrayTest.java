package ru.job4j.generics;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class SimpleArrayTest {

    @Test
    public void whenSet11Then11() {
        SimpleArray<Integer> sa = new SimpleArray<>(10);
        sa.add(1);
        sa.add(2);
        sa.set(0, 11);
        assertThat(sa.get(0), is(11));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenTrySet11OverSizeThenExc() {
        SimpleArray<Integer> sa = new SimpleArray<>(10);
        sa.add(1);
        sa.set(3, 11);
    }

    @Test
    public void whenRemoveFirstElem() {
        SimpleArray<Integer> sa = new SimpleArray<>(10);
        sa.add(1);
        sa.add(2);
        sa.remove(0);
        assertThat(sa.get(0), is(2));
    }
}