package com.crmclothing;

import com.crmclothing.model.ClothingItem;
import com.crmclothing.service.ClothingManager;
import com.crmclothing.service.ClothingSearch;
import com.crmclothing.service.FileStorage;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClothingManager manager = new ClothingManager();
        ClothingSearch search = new ClothingSearch();
        FileStorage storage = new FileStorage();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        manager.getAllClothing().addAll(storage.loadFromFile());

        while (running) {
            System.out.println("\n=== CRM Clothing Menu ===");
            System.out.println("1. Додати одяг");
            System.out.println("2. Показати одяг");
            System.out.println("3. Змінити одяг");
            System.out.println("4. Видалити одяг");
            System.out.println("5. Очистити список");
            System.out.println("6. Пошук одягу");
            System.out.println("7. Зберегти у файл");
            System.out.println("8. Вийти");
            System.out.print("Вибір: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Назва: ");
                    String name = scanner.nextLine();
                    System.out.print("Тип: ");
                    String type = scanner.nextLine();
                    System.out.print("Розмір: ");
                    String size = scanner.nextLine();
                    System.out.print("Колір: ");
                    String color = scanner.nextLine();
                    System.out.print("Принт: ");
                    String print = scanner.nextLine();
                    System.out.print("Дата додавання (напр. 2025-05-11): ");
                    String date = scanner.nextLine();

                    ClothingItem item = new ClothingItem(name, type, size, color, date, print);
                    manager.addClothing(item);
                    System.out.println("Одяг додано!");
                }
                case 2 -> {
                    List<ClothingItem> all = manager.getAllClothing();
                    if (all.isEmpty()) {
                        System.out.println("Список порожній.");
                    } else {
                        for (int i = 0; i < all.size(); i++) {
                            System.out.println(i + ":\n" + all.get(i) + "\n");
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Індекс одягу для зміни: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Нова назва: ");
                    String name = scanner.nextLine();
                    System.out.print("Новий тип: ");
                    String type = scanner.nextLine();
                    System.out.print("Новий розмір: ");
                    String size = scanner.nextLine();
                    System.out.print("Новий колір: ");
                    String color = scanner.nextLine();
                    System.out.print("Новий принт: ");
                    String print = scanner.nextLine();
                    System.out.print("Нова дата: ");
                    String date = scanner.nextLine();

                    ClothingItem newItem = new ClothingItem(name, type, size, color, date, print);
                    manager.editClothing(index, newItem);
                    System.out.println("Одяг змінено.");
                }
                case 4 -> {
                    System.out.print("Введіть індекс для видалення: ");
                    int index = scanner.nextInt();
                    manager.deleteClothing(index);
                    System.out.println("Одяг видалено.");
                }
                case 5 -> {
                    manager.clearClothingList();
                    System.out.println("Список очищено.");
                }
                case 6 -> {
                    System.out.println("Пошук за:\n1. Розмір\n2. Колір");
                    int opt = scanner.nextInt();
                    scanner.nextLine();
                    List<ClothingItem> found = null;
                    if (opt == 1) {
                        System.out.print("Введіть розмір: ");
                        String size = scanner.nextLine();
                        found = search.searchBySize(manager.getAllClothing(), size);
                    } else if (opt == 2) {
                        System.out.print("Введіть колір: ");
                        String color = scanner.nextLine();
                        found = search.searchByColor(manager.getAllClothing(), color);
                    } else {
                        System.out.println("Невірний варіант пошуку.");
                    }

                    if (found != null) {
                        if (found.isEmpty()) {
                            System.out.println("Нічого не знайдено.");
                        } else {
                            found.forEach(item -> System.out.println(item + "\n"));
                        }
                    }
                }
                case 7 -> {
                    storage.saveToFile(manager.getAllClothing());
                    System.out.println("Дані збережено у файл.");
                }
                case 8 -> {
                    running = false;
                    storage.saveToFile(manager.getAllClothing());
                    System.out.println("Вихід... Дані збережено.");
                }
                default -> System.out.println("Невірний вибір!");
            }
        }
        scanner.close();
    }
}
