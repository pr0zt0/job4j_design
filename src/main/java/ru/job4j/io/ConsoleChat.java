package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private final List<String> answers;
    private final List<String> logs = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        this.answers = readPhrases();
    }

    public void runApp() {
        String req = reqPhrase();
        logs.add(req);
        runChat(req);
    }

    private void runChat(String req) {
        boolean ans = true;
        while (!req.equals(OUT)) {
            if (req.equals(STOP)) {
                ans = false;
            } else if (req.equals(CONTINUE)) {
                ans = true;
            }
            botSay(ans);
            req = reqPhrase();
            logs.add(req);
        }
        saveLog(this.logs);
    }

    private void botSay(boolean ans) {
        if (ans) {
            int rnd = (int) (Math.random() * answers.size());
            String answer = this.answers.get(rnd);
            System.out.println(answer);
            logs.add(answer);
        }
    }

    private String reqPhrase() {
        return this.sc.nextLine();
    }

    private List<String> readPhrases() {
        List<String> botAnswers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.botAnswers))) {
            botAnswers = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botAnswers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(this.path, Charset.forName("Windows-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("answers.txt", "botPhrases.txt");
        cc.runApp();
    }
}