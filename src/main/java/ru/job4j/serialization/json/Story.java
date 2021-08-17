package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Story {
    private final String owner;
    private int numberTask;
    private Task[] tasks;

    public Story(String owner, int numberTask, Task[] tasks) {
        this.owner = owner;
        this.numberTask = numberTask;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Story{"
                + "owner='" + owner + '\''
                + ", numberTask=" + numberTask
                + ", tasks=" + Arrays.toString(tasks)
                + '}';
    }

    public static void main(String[] args) {
        Story story = new Story("TL", 2, new Task[] {new Task(false), new Task(false)});

        final Gson gson = new GsonBuilder().create();
        String storyJson = gson.toJson(story);
        System.out.println(storyJson);
        System.out.println(gson.fromJson(storyJson, Story.class));
    }
}
