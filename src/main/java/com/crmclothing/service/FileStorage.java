package com.crmclothing.service;

import com.crmclothing.model.ClothingItem;

import java.io.*;
import java.util.*;

public class FileStorage {
    private Map<Integer, ClothingItem> clothingMap = new HashMap<>();
    private int nextId = 1;
    private String fileName;

    public FileStorage(String fileName) {
        this.fileName = fileName;
        loadFromFile();
    }

    public void addClothing(ClothingItem item) {
        clothingMap.put(nextId++, item);
    }

    public void editClothing(int id, ClothingItem newItem) {
        if (clothingMap.containsKey(id)) {
            clothingMap.put(id, newItem);
        }
    }

    public void deleteClothing(int id) {
        clothingMap.remove(id);
    }

    public void listClothing() {
        if (clothingMap.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        for (Map.Entry<Integer, ClothingItem> entry : clothingMap.entrySet()) {
            System.out.println("ID: " + entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("----------");
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<Integer, ClothingItem> entry : clothingMap.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue().toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка при збереженні: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length != 2) continue;
                int id = Integer.parseInt(parts[0]);
                ClothingItem item = ClothingItem.fromCSV(parts[1]);
                if (item != null) {
                    clothingMap.put(id, item);
                    if (id >= nextId) nextId = id + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при завантаженні: " + e.getMessage());
        }
    }
}
