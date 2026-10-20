/*
package com.store;

public class Main {
    public static void main(String[] args) {

        // Test 1: thik product
        Product laptop = new Product("Laptop", "Electronics", 999.99, 5);
        System.out.println(laptop);

        // Test 2: invalid price
        try {
            Product bad = new Product("Pen", "Stationery", -5, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test 3: invalid stock
        try {
            laptop.setStockQuantity(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}



--------------------------

package com.store;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // ----- Product validation test -----
        try {
            new Product("Pen", "Stationery", -5, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ----- Inventory test -----
        StoreInventory inventory = new StoreInventory();

        // Empty inventory
        inventory.listInventory();

        // Product add
        inventory.addProduct(new Product("Laptop", "Electronics", 999.99, 5));
        inventory.addProduct(new Product("Mouse", "Electronics", 25.50, 40));
        inventory.addProduct(new Product("Notebook", "Stationery", 3.75, 100));
        inventory.listInventory();

        // Category search
        System.out.println("\nElectronics products:");
        List<Product> electronics = inventory.searchByCategory("electronics");
        for (Product p : electronics) {
            System.out.println(" - " + p);
        }

        // Remove (boro-chhoto hater ghoti nai)
        boolean removed = inventory.removeProduct("MOUSE");
        System.out.println("\nMouse removed? " + removed);

        // Je product nai seta remove kora
        boolean removedAgain = inventory.removeProduct("Keyboard");
        System.out.println("Keyboard removed? " + removedAgain);

        inventory.listInventory();
    }
}*/
package com.store;

public class Main {

    public static void main(String[] args) {

        StoreInventory inventory = new StoreInventory();

        // ----- Boundary test er product (price = subtotal boundary) -----
        inventory.addProduct(new Product("Tier-A", "Test", 99.99, 10));
        inventory.addProduct(new Product("Tier-B", "Test", 100.00, 10));
        inventory.addProduct(new Product("Tier-C", "Test", 199.99, 10));
        inventory.addProduct(new Product("Tier-D", "Test", 200.00, 10));
        inventory.addProduct(new Product("Tier-E", "Test", 499.99, 10));
        inventory.addProduct(new Product("Tier-F", "Test", 500.00, 10));

        // ----- Normal product -----
        inventory.addProduct(new Product("Mouse", "Electronics", 25.50, 40));
        inventory.addProduct(new Product("Laptop", "Electronics", 999.99, 5));

        System.out.println("--- Boundary tests (quantity 1) ---");
        placeOrder(inventory, "Tier-A", 1);   // 99.99  -> 0%
        placeOrder(inventory, "Tier-B", 1);   // 100.00 -> 5%
        placeOrder(inventory, "Tier-C", 1);   // 199.99 -> 5%
        placeOrder(inventory, "Tier-D", 1);   // 200.00 -> 10%
        placeOrder(inventory, "Tier-E", 1);   // 499.99 -> 10%
        placeOrder(inventory, "Tier-F", 1);   // 500.00 -> 15%

        System.out.println("\n--- Quantity diye order ---");
        placeOrder(inventory, "Mouse", 10);   // 255.00 -> 10%
        placeOrder(inventory, "laptop", 2);   // case-insensitive, 1999.98 -> 15%

        System.out.println("\n--- Invalid order ---");
        placeOrder(inventory, "Laptop", 10);    // stock kom
        placeOrder(inventory, "Keyboard", 1);   // product nai
        placeOrder(inventory, "Mouse", 0);      // quantity 0

        System.out.println();
        inventory.listInventory();
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
}

