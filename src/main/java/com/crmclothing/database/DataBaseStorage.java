package com.crmclothing.database;

import com.crmclothing.model.ClothingItem;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseStorage {
    private static final String URL = "jdbc:sqlite:database.db";

    public DataBaseStorage() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS clothing (" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT," +
                    "type TEXT," +
                    "size INTEGER," +
                    "color TEXT," +
                    "print TEXT," +
                    "dateAdded TEXT"+
                    ")";
            stmt.execute(sql);

            try {
                String alterSql = "ALTER TABLE clothing ADD COLUMN dateAdded TEXT";
                stmt.execute(alterSql);
            } catch (SQLException e) {
                if (!e.getMessage().contains("duplicate column name")) {
                    throw e;
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Помилка створення таблиці: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void save(Map<Integer, ClothingItem> clothingMap) {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM clothing");

            String sql = "INSERT INTO clothing (id, name, type, size, color, print, dateAdded) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (ClothingItem item : clothingMap.values()) {
                    pstmt.setInt(1, item.getId());
                    pstmt.setString(2, item.getName());
                    pstmt.setString(3, item.getType());
                    pstmt.setString(4, item.getSize());
                    pstmt.setString(5, item.getColor());
                    pstmt.setString(6, item.getPrint());
                    pstmt.setString(7, item.getDateAdded());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Помилка збереження у базу: " + e.getMessage());
        }
    }

    public Map<Integer, ClothingItem> load() {
        Map<Integer, ClothingItem> clothingMap = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM clothing")) {

            while (rs.next()) {
                ClothingItem item = new ClothingItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("size"),
                        rs.getString("color"),
                        rs.getString("print"),
                        rs.getString("dateAdded")
                );
                clothingMap.put(item.getId(), item);
            }

        } catch (SQLException e) {
            System.out.println("Помилка завантаження з бази: " + e.getMessage());
        }

        return clothingMap;
    }
}