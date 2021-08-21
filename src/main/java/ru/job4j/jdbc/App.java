package ru.job4j.jdbc;

public class App {
    public static void main(String[] args) {
        try {
            PrepareStatementDemo psd = new PrepareStatementDemo();
            psd.initConnection();
            City city = new City(1, "One", 20);
            psd.insert(city);
            psd.update(new City(1, "123", 20));
            System.out.println(psd.findAll());
            psd.delete(1);
            System.out.println(psd.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
