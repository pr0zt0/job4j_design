package ru.job4j.generics;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class MemStoreTest {

    @Test
    public void whenAdd1AndDelete1ThenTrue() {
        MemStore<Base> ms = new MemStore<>();
        ms.add(new Base("1"));
        assertTrue(ms.delete("1"));
    }

    @Test
    public void whenAdd1AndDelete2ThenFalse() {
        MemStore<Base> ms = new MemStore<>();
        ms.add(new Base("1"));
        assertFalse(ms.delete("2"));
    }

    @Test
    public void whenAdd2AndReplace2ThenTrue() {
        MemStore<Base> ms = new MemStore<>();
        ms.add(new Base("2"));
        assertTrue(ms.replace("2", new Base("123123")));
    }

    @Test
    public void whenAdd2AndReplace22ThenTrue() {
        MemStore<Base> ms = new MemStore<>();
        ms.add(new Base("2"));
        assertFalse(ms.replace("22", new Base("123123")));
    }

    @Test
    public void whenAdd2andFind2Then2() {
        MemStore<Base> ms = new MemStore<>();
        Base bs = new Base("2");
        ms.add(bs);
        assertThat(ms.findById("2"), is(bs));
    }
}