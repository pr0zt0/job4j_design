package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Story {
    private final String owner;
    private int numberTask;
    private Task[] tasks;

    public Story(String owner, int numberTask, Task[] tasks) {
        this.owner = owner;
        this.numberTask = numberTask;
        this.tasks = tasks;
    }

    public String getOwner() {
        return owner;
    }

    public int getNumberTask() {
        return numberTask;
    }

    public Task[] getTasks() {
        return tasks;
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

        System.out.println();

        List<String> list = new ArrayList<>();
        list.add(story.getTasks()[0].toString());
        list.add(story.getTasks()[1].toString());
        JSONArray jsonArray = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("owner", story.getOwner());
        jsonObject.put("numberTask", story.getNumberTask());
        jsonObject.put("tasks", jsonArray);

        System.out.println(jsonObject.toString());
    }
}
