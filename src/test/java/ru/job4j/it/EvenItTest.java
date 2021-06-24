package ru.job4j.it;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.NoSuchElementException;

public class EvenItTest {

    @Test(expected = NoSuchElementException.class)
    public void whenNoEvenNumberThenException() {
        EvenIt it = new EvenIt(new int[] {1, 1, 1});
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoNumberThenException() {
        EvenIt it = new EvenIt(new int[] {});
        it.next();
    }

    @Test
    public void when2times2Then2t2() {
        EvenIt it = new EvenIt(new int[] {2, 2});
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(2));
    }

    @Test
    public void when21112Then22() {
        EvenIt it = new EvenIt(new int[] {2, 1, 1, 1, 2});
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(2));
    }
}