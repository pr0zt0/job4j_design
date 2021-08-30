package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Set<FileProperty> set = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty fp = new FileProperty(file.toFile().getTotalSpace(), file.getFileName().toString());
            if (set.contains(fp)) {
                Files.delete(file);
            } else {
                set.add(fp);
            }
        }
        return super.visitFile(file, attrs);
    }
}