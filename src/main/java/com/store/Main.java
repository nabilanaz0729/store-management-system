package com.store;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        StoreInventory inventory = new StoreInventory();

        // ================= 1. ADD PRODUCTS =================
        printHeader("1. Adding products");
        inventory.addProduct(new Product("Laptop", "Electronics", 999.99, 5));
        inventory.addProduct(new Product("Mouse", "Electronics", 25.50, 40));
        inventory.addProduct(new Product("Headphones", "Electronics", 59.99, 15));
        inventory.addProduct(new Product("Notebook", "Stationery", 3.75, 100));
        inventory.addProduct(new Product("Pen", "Stationery", 1.20, 500));
        inventory.listInventory();

        // ================= 2. VALIDATION EDGE CASES =================
        printHeader("2. Validation edge cases");

        try {
            new Product("Bad", "Test", -5, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Negative price  -> " + e.getMessage());
        }

        try {
            new Product("Bad", "Test", 0, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Zero price      -> " + e.getMessage());
        }

        try {
            new Product("Bad", "Test", 10, -3);
        } catch (IllegalArgumentException e) {
            System.out.println("Negative stock  -> " + e.getMessage());
        }

        try {
            inventory.findProduct("Laptop").setPrice(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Setter with 0   -> " + e.getMessage());
        }

        try {
            inventory.addProduct(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Null product    -> " + e.getMessage());
        }

        // ================= 3. SEARCH AND REMOVE =================
        printHeader("3. Search and remove");

        List<Product> electronics = inventory.searchByCategory("electronics");
        System.out.println("Electronics found: " + electronics.size());
        for (Product p : electronics) {
            System.out.println("  " + p);
        }

        System.out.println("Remove 'PEN' (case-insensitive): " + inventory.removeProduct("PEN"));
        System.out.println("Remove 'Tablet' (not in store): " + inventory.removeProduct("Tablet"));

        // ================= 4. ORDERS WITH DISCOUNTS =================
        printHeader("4. Orders with discounts");

        placeOrder(inventory, "Notebook", 10);     // 37.50   -> 0%
        placeOrder(inventory, "Headphones", 2);    // 119.98  -> 5%
        placeOrder(inventory, "Mouse", 10);        // 255.00  -> 10%
        placeOrder(inventory, "Laptop", 1);        // 999.99  -> 15%

        // Invalid orders
        placeOrder(inventory, "Laptop", 50);       // stock kom
        placeOrder(inventory, "Tablet", 1);        // product nai
        placeOrder(inventory, "Mouse", 0);         // quantity 0

        inventory.listInventory();

        // ================= 5. SAVE TO FILE =================
        printHeader("5. Saving to file");
        String filePath = "inventory.json";

        try {
            InventoryStorage.saveInventoryToFile(inventory, filePath);
            System.out.println("Inventory saved to " + filePath);

            // ================= 6. LOAD AND VERIFY =================
            printHeader("6. Restoring from file");
            StoreInventory restored = InventoryStorage.loadInventoryFromFile(filePath);
            restored.listInventory();

            boolean sameData = inventory.getProducts().size() == restored.getProducts().size();
            boolean distinct = true;

            for (Product original : inventory.getProducts()) {
                Product copy = restored.findProduct(original.getName());
                if (copy == null || !original.toString().equals(copy.toString())) {
                    sameData = false;
                }
                if (copy == original) {
                    distinct = false;
                }
            }
            System.out.println("Restored data matches original: " + sameData);
            System.out.println("Restored objects are distinct from original: " + distinct);

        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ================= 7. MISSING FILE =================
        printHeader("7. Loading a missing file");
        try {
            InventoryStorage.loadInventoryFromFile("missing.json");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getClass().getSimpleName()
                    + " - " + e.getMessage());
        }
    }

    // Order dey, result print kore. Error hole message dekhay
    private static void placeOrder(StoreInventory inventory, String name, int quantity) {
        try {
            OrderSummary summary = inventory.createOrder(name, quantity);
            System.out.println(summary);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void printHeader(String title) {
        System.out.println("\n>>> " + title);
    }
}