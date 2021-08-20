package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"), properties.getProperty("password"));
    }

    private void sampleQuery(String query, String tableName) throws Exception {
        try (Statement st = connection.createStatement()) {
            st.execute(query);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public void createTable(String tableName) throws Exception {
        sampleQuery(String.format(
                "create table if not exists %s",
                tableName
        ), tableName);
    }

    public void dropTable(String tableName) throws Exception {
        sampleQuery(String.format(
                "drop table if exists %s",
                tableName
        ), tableName);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        sampleQuery(String.format(
                "alter table %s add column if not exists %s %s",
                tableName,
                columnName,
                type
        ), tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        sampleQuery(String.format(
                "alter table %s drop column %s",
                tableName,
                columnName
        ), tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        sampleQuery(String.format(
                "alter table %s rename column %s to %s",
                tableName,
                columnName,
                newColumnName
        ), tableName);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}