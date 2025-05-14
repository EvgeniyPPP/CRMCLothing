package com.crmclothing.service;

import com.crmclothing.model.ClothingItem;

import java.util.List;
import java.util.stream.Collectors;

public class ClothingSearch {

    public List<ClothingItem> searchBySize(List<ClothingItem> items, int size) {
        return items.stream()
                .filter(item -> item.getSize() == size)
                .collect(Collectors.toList());
    }

    public List<ClothingItem> searchByColor(List<ClothingItem> items, String color) {
        return items.stream()
                .filter(item -> item.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}
