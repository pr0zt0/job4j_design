package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);

        String fullName = "John Snow";
        int height = 180;
        double weight = 23.2d;
        float smile = 123.3f;
        boolean alone = true;
        char gender = 'm';
        long steps = 1234553L;
        byte b = 24;
        LOG.debug("User info name : {}, height : {}, weight {}, smile {}, alone {}"
                        + " gender {}, steps {}, b {}", fullName, height, weight,
                smile, alone, gender, steps, b);
    }
}