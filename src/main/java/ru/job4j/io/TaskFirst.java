package ru.job4j.io;

import java.io.FileOutputStream;

public class TaskFirst {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("multi.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    String res = i + " * " + j + " = " + i * j;
                    out.write(res.getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
