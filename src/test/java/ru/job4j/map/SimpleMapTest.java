package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenAdd() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap();
        simpleMap.put(1, 2);
        assertThat(simpleMap.get(1), is(Integer.valueOf(2)));
    }

    @Test
    public void whenAddSimilar() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap();
        simpleMap.put(1, 2);
        simpleMap.put(1, 3);
        assertThat(simpleMap.get(1), is(Integer.valueOf(3)));
    }

    @Test
    public void whenAddTwice() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap();
        simpleMap.put(1, 2);
        simpleMap.put(2, 3);
        assertThat(simpleMap.get(1), is(Integer.valueOf(2)));
        assertThat(simpleMap.get(2), is(Integer.valueOf(3)));
    }

    @Test
    public void whenGetNotIncludeNumber() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap();
        simpleMap.put(1, 2);
        simpleMap.put(2, 3);
        assertNull(simpleMap.get(4));
    }

    @Test
    public void checkIterator() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap();
        simpleMap.put(1, 5);
        simpleMap.put(2, 3);

        Iterator<Integer> first = simpleMap.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(1));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(2));
        assertThat(first.hasNext(), Is.is(false));

        Iterator<Integer> second = simpleMap.iterator();
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(1));
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(2));
        assertThat(second.hasNext(), Is.is(false));
    }
}