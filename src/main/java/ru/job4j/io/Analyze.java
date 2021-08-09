package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analyze {
    public static void unavailable(String source, String target) {
        List<String> result;
        List<String> total = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            result = in.lines().collect(Collectors.toList());
            for (int i = 0; i < result.size(); i++) {
                String str = result.get(i).split(" ")[0];
                if (str.equals("400") || str.equals("500")) {
                    String strPeriod = result.get(i).split(" ")[1];
                    while (i < result.size()) {
                        str = result.get(i).split(" ")[0];
                        if (str.equals("200") || str.equals("300")) {
                            String endPeriod = result.get(i).split(" ")[1];
                            total.add(strPeriod);
                            total.add(endPeriod);
                            break;
                        }
                        i++;
                    }
                }
            }
            try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
                for (int i = 0; i < total.size(); i++) {
                    out.println(total.get(i) + ";" + total.get(i++));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("dump2.csv", "new_dump.csv");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
}