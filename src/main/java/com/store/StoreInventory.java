package com.store;

import java.util.ArrayList;
import java.util.List;

public class StoreInventory {

    // Shob product ekhane thakbe (private, baire theke dhora jabe na)
    private final List<Product> products = new ArrayList<>();

    // 1. Product add kora
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    // 2. Nam diye product khuje ber kora (case-insensitive)
    //    Pele Product return kore, na pele null
    public Product findProduct(String productName) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(productName)) {
                return p;
            }
        }
        return null;
    }

    // 3. Nam diye product remove kora
    //    Remove hole true, na pele false
    public boolean removeProduct(String productName) {
        Product found = findProduct(productName);
        if (found == null) {
            return false;
        }
        products.remove(found);
        return true;
    }

    // 4. Category diye search kora
    public List<Product> searchByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    // 5. Shob product dekhano
    public void listInventory() {
        System.out.println("===== STORE INVENTORY =====");
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            int number = 1;
            for (Product p : products) {
                System.out.println(number + ". " + p);
                number++;
            }
        }
        System.out.println("===========================");
    }

    // 6. Shob product er copy dey (file e save korar somoy lagbe)
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}
