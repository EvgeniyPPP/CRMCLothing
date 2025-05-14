package com.crmclothing.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    public static Connection connect(String dbName) throws SQLException {
        String url = "jdbc:sqlite:" + dbName;
        return DriverManager.getConnection(url);
    }

    public static void initializeDatabase(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS clothing (
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                type TEXT NOT NULL,
                size INTEGER NOT NULL,
                color TEXT NOT NULL,
                print TEXT,
                dateAdded TEXT NOT NULL
            );
        """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
}
