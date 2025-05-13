package com.crmclothing.service;

import com.crmclothing.model.ClothingItem;

import java.util.ArrayList;
import java.util.List;

public class ClothingManager {
    private List<ClothingItem> clothingList;

    public ClothingManager() {
        this.clothingList = new ArrayList<>();
    }

    public void addClothing(ClothingItem item) {
        clothingList.add(item);
    }

    public List<ClothingItem> getAllClothing() {
        return clothingList;
    }

    public void editClothing(int index, ClothingItem newItem) {
        if (index >= 0 && index < clothingList.size()) {
            clothingList.set(index, newItem);
        }
    }

    public void deleteClothing(int index) {
        if (index >= 0 && index < clothingList.size()) {
            clothingList.remove(index);
        }
    }

    public void clearClothingList() {
        clothingList.clear();
    }

    public int getSize() {
        return clothingList.size();
    }
}
