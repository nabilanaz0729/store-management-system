/*package com.store;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

    }
}*/

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