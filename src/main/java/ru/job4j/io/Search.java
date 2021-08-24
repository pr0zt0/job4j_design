package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {
    public static void main(String[] args) throws IOException {
        Path startNew = Paths.get(".");
        Files.walkFileTree(startNew, new PrintFiles());
        System.out.println();
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith("js")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static class SearchFiles implements FileVisitor<Path> {
        private Predicate<Path> predicate;
        private List<Path> pathsList;

        public SearchFiles(Predicate<Path> predicate) {
            this.predicate = predicate;
            this.pathsList = new ArrayList<>();
        }

        public List<Path> getPaths() {
            return pathsList;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (predicate.test(file.getFileName())) {
                System.out.println(file.toAbsolutePath());
                pathsList.add(file.toAbsolutePath());
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return CONTINUE;
        }
    }
}