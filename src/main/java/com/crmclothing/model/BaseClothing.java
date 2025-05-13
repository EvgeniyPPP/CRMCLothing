package com.crmclothing.model;

public abstract class BaseClothing {
    private String name;
    private String type;
    private String size;
    private String color;
    private String print;
    private String dateAdded;

    public BaseClothing(String name, String type, String size, String color, String dateAdded, String print) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.dateAdded = dateAdded;
        this.print = print;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getPrint() { return print; }
    public void setPrint(String print) { this.print = print; }

    public String getDateAdded() { return dateAdded; }

    public abstract String display();
}
