package com.crmclothing;

import com.crmclothing.model.ClothingItem;
import com.crmclothing.service.DatabaseStorage;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DatabaseStorage storage = new DatabaseStorage();
    private static Map<Integer, ClothingItem> clothingMap = new HashMap<>(storage.load());

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Додати одяг");
            System.out.println("2. Редагувати одяг");
            System.out.println("3. Видалити одяг");
            System.out.println("4. Показати всі записи");
            System.out.println("5. Зберегти у базу даних");
            System.out.println("6. Вийти");
            System.out.print("Ваш вибір: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addItem();
                case "2" -> editItem();
                case "3" -> deleteItem();
                case "4" -> listItems();
                case "5" -> storage.save(clothingMap);
                case "6" -> {
                    storage.save(clothingMap);
                    System.out.println("Збережено. До побачення!");
                    return;
                }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }

    private static void addItem() {
        ClothingItem item = createClothingItem();
        int newId = clothingMap.isEmpty() ? 1 : clothingMap.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
        item.setId(newId);
        clothingMap.put(newId, item);
        System.out.println("✔ Додано.");
    }

    private static void editItem() {
        System.out.print("Введіть ID для редагування: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (!clothingMap.containsKey(id)) {
                System.out.println("Такого ID не існує.");
                return;
            }
            ClothingItem newItem = createClothingItem();
            newItem.setId(id);
            clothingMap.put(id, newItem);
            System.out.println("✔ Змінено.");
        } catch (NumberFormatException e) {
            System.out.println("ID має бути числом.");
        }
    }

    private static void deleteItem() {
        System.out.print("Введіть ID для видалення: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (clothingMap.remove(id) != null) {
                System.out.println("✔ Видалено.");
            } else {
                System.out.println("Такого ID не існує.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID має бути числом.");
        }
    }

    private static void listItems() {
        if (clothingMap.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        clothingMap.values().forEach(item -> {
            System.out.println(item);
            System.out.println("-----------");
        });
    }

    private static ClothingItem createClothingItem() {
        System.out.print("Назва: ");
        String name = scanner.nextLine();

        System.out.print("Тип: ");
        String type = scanner.nextLine();

        int size = 0;
        while (true) {
            try {
                System.out.print("Розмір (число): ");
                size = Integer.parseInt(scanner.nextLine());
                if (size > 0 && size < 100) break;
                else System.out.println("Розмір повинен бути від 1 до 99.");
            } catch (NumberFormatException e) {
                System.out.println("Розмір має бути числом.");
            }
        }

        System.out.print("Колір: ");
        String color = scanner.nextLine();

        System.out.print("Принт: ");
        String print = scanner.nextLine();

        System.out.print("Дата додавання (YYYY-MM-DD): ");
        String dateAdded = scanner.nextLine();

        return new ClothingItem(name, type, size, color, print, dateAdded);
    }
}
