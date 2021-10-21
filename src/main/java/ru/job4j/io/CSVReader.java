package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<String[]> data = new ArrayList<>();
        if (!argsName.get("out").equals("stdout")
                && new File(argsName.get("out")).isDirectory()) {
            throw new IllegalArgumentException("wrong -out key");
        }
        Scanner sc = new Scanner(new FileInputStream(argsName.get("path"))).useDelimiter("\r\n");
        while (sc.hasNext()) {
            data.add(sc.next().split(argsName.get("delimiter")));
        }
        List<String> correctWords = List.of(argsName.get("filter").split(","));
        List<Integer> positions = new ArrayList<>();
        for (String needColumn : correctWords) {
            positions.add(Arrays.asList(data.get(0)).indexOf(needColumn));
        }
        List<String> dataShow = new ArrayList<>();
        for (String[] newDataLine : data) {
            StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
            for (Integer i : positions) {
                joiner.add(Arrays.asList(newDataLine).get(i));

            }
            dataShow.add(joiner.toString());
        }
        showCSVFile(dataShow, argsName.get("out"));
    }

    private static void showCSVFile(List<String> data, String path) {
        if (path.equals("stdout")) {
            data.forEach(System.out::println);
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
                data.forEach(pw::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}