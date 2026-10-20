/*package com.store;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

    }
}

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



--------------------------*/

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
}

