package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongMatches(){
        String path = "./data/wrong.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenReadWithCommentAndSpace() {
        String path = "./data/comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("some"), is("2"));
        assertThat(config.value("home"), is("21"));
    }
}