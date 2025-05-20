package com.crmclothing.model;

public class ClothingItem {
    private int id;
    private String name;
    private String type;
    private String size;
    private String color;
    private String print;
    private String dateAdded;

    public ClothingItem(int id, String name, String type, String size, String color, String print, String dateAdded) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.print = print;
        this.dateAdded = dateAdded;
    }

    public ClothingItem(String name, String type, String size, String color, String print, String dateAdded) {
        this(0, name, type, size, color, print, dateAdded);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getPrint() {
        return print;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String toCSV() {
        return id + "," + name + "," + type + "," + size + "," + color + "," + print + "," + dateAdded;
    }

    public static ClothingItem fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 7) return null;

        try {
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String type = parts[2];
            String size = parts[3];
            String color = parts[4];
            String print = parts[5];
            String dateAdded = parts[6];
            return new ClothingItem(id, name, type, size, color, print, dateAdded);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Назва: " + name + "\n" +
                "Тип: " + type + "\n" +
                "Розмір: " + size + "\n" +
                "Колір: " + color + "\n" +
                "Принт: " + print + "\n" +
                "Дата додавання: " + dateAdded;
    }
}