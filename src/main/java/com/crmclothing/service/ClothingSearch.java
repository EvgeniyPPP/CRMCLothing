package com.crmclothing.service;

import com.crmclothing.model.ClothingItem;

import java.util.ArrayList;
import java.util.List;

public class ClothingSearch {

    public List<ClothingItem> searchBySize(List<ClothingItem> clothingList, String size) {
        List<ClothingItem> result = new ArrayList<>();
        for (ClothingItem item : clothingList) {
            if (item.getSize().equalsIgnoreCase(size)) {
                result.add(item);
            }
        }
        return result;
    }

    public List<ClothingItem> searchByColor(List<ClothingItem> clothingList, String color) {
        List<ClothingItem> result = new ArrayList<>();
        for (ClothingItem item : clothingList) {
            if (item.getColor().equalsIgnoreCase(color)) {
                result.add(item);
            }
        }
        return result;
    }
}
