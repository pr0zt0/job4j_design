package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "task")
public class Task {

    @XmlAttribute
    private boolean isClose;

    public Task() {
    }

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
