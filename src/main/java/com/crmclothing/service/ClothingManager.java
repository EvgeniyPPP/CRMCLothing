package com.crmclothing.service;

import com.crmclothing.model.ClothingItem;

import java.util.HashMap;
import java.util.Map;

public class ClothingManager {
    private Map<Integer, ClothingItem> clothingMap = new HashMap<>();

    public void addClothing(ClothingItem item) {
        clothingMap.put(item.getId(), item);
    }

    public void editClothing(int id, ClothingItem newItem) {
        clothingMap.put(id, newItem);
    }

    public void deleteClothing(int id) {
        clothingMap.remove(id);
    }

    public void clearClothing() {
        clothingMap.clear();
    }

    public Map<Integer, ClothingItem> getAllClothing() {
        return clothingMap;
    }

    public boolean exists(int id) {
        return clothingMap.containsKey(id);
    }
}
