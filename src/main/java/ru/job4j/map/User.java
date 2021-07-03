package ru.job4j.map;

import java.util.Calendar;
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
        Calendar usDate = Calendar.getInstance();
        usDate.set(Calendar.YEAR, 2000);
        usDate.set(Calendar.MONTH, 4);
        User us1 = new User("Bob", 2, usDate);

        Calendar usDate2 = Calendar.getInstance();
        usDate.set(Calendar.YEAR, 1893);
        usDate.set(Calendar.MONTH, 1);
        User us2 = new User("Tom", 0, usDate2);

        Map<User, Object> map = new HashMap<>();
        map.put(us1, new Object());
        map.put(us2, new Object());
        for (Map.Entry<User, Object> it : map.entrySet()) {
            System.out.println("Key : " + it.getKey() + " value: " + it.getValue());
        }
        System.out.println(us1.hashCode());
        System.out.println(us2.hashCode());
    }
}
