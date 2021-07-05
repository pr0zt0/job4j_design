package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 0, 3);
        assertThat(Arrays.asList(0, 3, 1, 2), Is.is(input));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.removeIf(input, i -> i < 1);
        assertThat(Arrays.asList(1, 2), Is.is(input));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.replaceIf(input, i -> i < 2, 3);
        assertThat(Arrays.asList(3, 3, 2), Is.is(input));
    }


    @Test
    public void whenRemoveAll() {
        List<Integer> input1 = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        List<Integer> input2 = new ArrayList<>(Arrays.asList(0, 2));
        ListUtils.removeAll(input1, input2);
        assertThat(Arrays.asList(1, 3), Is.is(input1));
    }
}