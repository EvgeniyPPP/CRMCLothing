package com.crmclothing.service;

import com.crmclothing.model.ClothingItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private static final String FILE_NAME = "clothing_data.txt";

    public void saveToFile(List<ClothingItem> clothingList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (ClothingItem item : clothingList) {
                writer.write(item.getName() + ";" + item.getType() + ";" + item.getSize() + ";" +
                        item.getColor() + ";" + item.getPrint() + ";" + item.getDateAdded());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка збереження у файл: " + e.getMessage());
        }
    }

    public List<ClothingItem> loadFromFile() {
        List<ClothingItem> clothingList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    ClothingItem item = new ClothingItem(parts[0], parts[1], parts[2], parts[3], parts[5], parts[4]);
                    clothingList.add(item);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка завантаження з файлу: " + e.getMessage());
        }
        return clothingList;
    }
}
