package com.crmclothing.model;

public class ClothingItem extends BaseClothing {

    public ClothingItem(String name, String type, String size, String color, String dateAdded, String print) {
        super(name, type, size, color, dateAdded, print);
    }

    @Override
    public String display() {
        return "Назва: " + getName() + "\n" +
                "Тип: " + getType() + "\n" +
                "Розмір: " + getSize() + "\n" +
                "Колір: " + getColor() + "\n" +
                "Принт: " + getPrint() + "\n" +
                "Дата додавання: " + getDateAdded();
    }

    @Override
    public String toString() {
        return display();
    }
}
