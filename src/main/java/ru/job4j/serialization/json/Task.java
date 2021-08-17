package ru.job4j.serialization.json;

public class Task {

    private boolean isClose;

    public Task(boolean isClose) {
        this.isClose = isClose;
    }

    @Override
    public String toString() {
        return "Task{"
                + "isClose=" + isClose
                + '}';
    }
}
