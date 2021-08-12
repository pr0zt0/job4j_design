package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analyze {
    public static void unavailable(String source, String target) {
        writeResult(target, findDownTime(source));
    }

    private static List<String> findDownTime(String source) {
        List<String> total = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String str;
            while ((str = in.readLine()) != null) {
                String codeServer = findData(str, 0);
                if (codeServer.equals("400") || codeServer.equals("500")) {
                    String strPeriod = findData(str, 1);
                    total.add(strPeriod);
                    while ((str = in.readLine()) != null) {
                        codeServer = findData(str, 0);
                        if (codeServer.equals("200") || codeServer.equals("300")) {
                            String endPeriod = findData(str, 1);
                            total.add(endPeriod);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    private static String findData(String str, int position) {
        return str.split(" ")[position];
    }

    private static void writeResult(String target, List<String> total) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (int i = 0; i < total.size(); i++) {
                out.println(total.get(i) + ";" + total.get(++i));
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