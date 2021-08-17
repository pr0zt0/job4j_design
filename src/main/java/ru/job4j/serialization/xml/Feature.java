package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Task;

import java.util.Date;

public class Feature {
    private final int numStory;
    private final String nameFeature;
    private final Task task;
    private final Date[] checkPoints;
    private boolean isClose;

    public Feature(int numStory, String nameFeature, Task task, Date[] checkPoints, boolean isClose) {
        this.numStory = numStory;
        this.nameFeature = nameFeature;
        this.task = task;
        this.checkPoints = checkPoints;
        this.isClose = isClose;
    }

    public static void main(String[] args) {
        Feature ft = new Feature(2, "New Mega Feature Ever", new Task(false),
                new Date[]{new Date(2021, 01, 01), new Date(2031, 01, 01)}, false);
    }
}
