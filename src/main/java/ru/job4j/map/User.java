package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.YEAR, 2000);
        cl.set(Calendar.MONTH, 2);

        User us1 = new User("Bob", 2, cl);
        User us2 = new User("Bob", 2, cl);
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
