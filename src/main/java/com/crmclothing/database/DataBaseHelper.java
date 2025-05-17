package com.crmclothing.database;

import java.sql.*;

public class DataBaseHelper {
    private static final String URL = "jdbc:sqlite:database.db";

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS clothing (
                        id INTEGER PRIMARY KEY,
                        name TEXT NOT NULL,
                        type TEXT NOT NULL,
                        size INTEGER,
                        color TEXT,
                        print TEXT,
                        date_added TEXT
                    );
                """);

                System.out.println("✔ Таблиця 'clothing' готова!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Помилка при створенні таблиці: " + e.getMessage());
        }
    }
}
