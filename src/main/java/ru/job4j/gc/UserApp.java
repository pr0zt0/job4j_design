package ru.job4j.gc;

public class UserApp {
    public static void main(String[] args) {
        User bob = new User("Bob", 12134, true);
        for (int i = 0; i < 200_000; i++) {
            new User("name " + i, (int) Math.pow(i, 4), true);
        }
    }
}