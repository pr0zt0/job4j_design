package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values = read.lines()
                    .filter(s -> s.contains("="))
                    .peek(s -> {
                                if (s.startsWith("=") || s.endsWith("=")) {
                                    throw new IllegalArgumentException();
                                }
                            }
                    )
                    .collect(Collectors.toMap(
                            s -> s.substring(0, s.indexOf("=")),
                            s -> s.substring(s.lastIndexOf("=") + 1)
                    ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
        Config cf = new Config("app.properties");
        cf.load();
        System.out.println(cf.values);
    }
}