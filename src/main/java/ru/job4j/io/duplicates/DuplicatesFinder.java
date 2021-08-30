package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        String pathName = args[0] != null ? args[0] : "./";
        Files.walkFileTree(Path.of(pathName), new DuplicatesVisitor());
    }
}