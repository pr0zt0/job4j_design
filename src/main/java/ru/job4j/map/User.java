package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, int year, int month) {
        this.name = name;
        this.children = children;
        this.birthday = configDay(year, month);
    }

    private Calendar configDay(int year, int month) {
        Calendar bDay = Calendar.getInstance();
        bDay.set(Calendar.YEAR, year);
        bDay.set(Calendar.MONTH, month);
        return bDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children;
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
    }

    public static void main(String[] args) {
        User us1 = new User("Bob", 2, 2000, 2);
        User us2 = new User("Bob", 2, 2000, 2);
        Object obj1 = new Object();
        Object obj2 = new Object();
        Map<User, Object> map = new HashMap<>();
        map.put(us1, obj1);
        map.put(us2, obj2);
        for (Map.Entry<User, Object> it : map.entrySet()) {
            System.out.println("Key : " + it.getKey() + " value: " + it.getValue());
        }
        System.out.println("First user: " + us1.hashCode());
        System.out.println("Second user: " + us2.hashCode());
        System.out.println();
        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
    }
}
