package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added;
        int changed = 0;
        int deleted;
        
        Set<User> temp = new HashSet<>(previous);
        Map<Integer, String> map = new HashMap<>();

        for (User user : current) {
            map.put(user.getId(), user.getName());
        }

        for (User user : previous) {
            if (map.containsKey(user.getId()) && map.get(user.getId()).equals(user.getName())) {
                map.remove(user.getId());
                temp.remove(user);
            } else if (map.containsKey(user.getId()) && !map.get(user.getId()).equals(user.getName())) {
                changed++;
            }
        }

        added = map.size() - changed;
        deleted = temp.size() - changed;

        return new Info(added, changed, deleted);
    }
}