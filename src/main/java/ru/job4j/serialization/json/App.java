package ru.job4j.serialization.json;

import org.json.JSONObject;

public class App {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Apple apple = new Apple();

        tree.setApple(apple);
        apple.setTree(tree);

        System.out.println(new JSONObject(tree));
    }
}
