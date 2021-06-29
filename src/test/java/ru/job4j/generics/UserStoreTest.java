package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenAdd1AndDelete1ThenTrue() {
        UserStore us = new UserStore();
        us.add(new User("1"));
        assertTrue(us.delete("1"));
    }

    @Test
    public void whenAdd1AndDelete2ThenFalse() {
        UserStore us = new UserStore();
        us.add(new User("1"));
        assertFalse(us.delete("2"));
    }

    @Test
    public void whenAdd2AndReplace2ThenTrue() {
        UserStore us = new UserStore();
        us.add(new User("2"));
        assertTrue(us.replace("2", new User("123123")));
    }

    @Test
    public void whenAdd2AndReplace22ThenTrue() {
        UserStore us = new UserStore();
        us.add(new User("2"));
        assertFalse(us.replace("22", new User("123123")));
    }

    @Test
    public void whenAdd2andFind2Then2() {
        UserStore us = new UserStore();
        User user = new User("2");
        us.add(user);
        assertThat(us.findById("2"), is(user));
    }
}