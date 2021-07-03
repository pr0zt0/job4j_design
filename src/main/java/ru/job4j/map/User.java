package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User us1 = new User("Bob", 2, new GregorianCalendar(-988, Calendar.JANUARY , 25));
        User us2 = new User("Tom", 0, new GregorianCalendar(2000, Calendar.JANUARY , 25));
        Map<User, Object> map = new HashMap<>();
        map.put(us1, new Object());
        map.put(us2, new Object());
        for (Map.Entry<User, Object> it : map.entrySet()) {
            System.out.println("Key : " + it.getKey() + " value: " + it.getValue());
        }
    }
}
