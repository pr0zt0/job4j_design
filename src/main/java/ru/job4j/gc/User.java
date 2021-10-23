package ru.job4j.gc;

public class User {
    private static int destoy = 0;
    private final String name;
    private final int id;
    private final boolean isOld;

    public User(String name, int id, boolean isOld) {
        this.name = name;
        this.id = id;
        this.isOld = isOld;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() {
        destoy++;
        System.out.println("Destroy " + destoy
                + " id " + this.id
                + " name " + this.name);
    }
}
