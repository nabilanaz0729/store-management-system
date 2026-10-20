package com.store;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InventoryStorage {

    // Gson object, setPrettyPrinting() mane file ta sundor kore shajano thakbe
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // ===== SAVE =====
    public static void saveInventoryToFile(StoreInventory inventory, String filePath)
            throws IOException {
        String json = GSON.toJson(inventory.getProducts());
        Files.writeString(Path.of(filePath), json);
    }

    // ===== LOAD =====
    public static StoreInventory loadInventoryFromFile(String filePath)
            throws IOException {

        String json = Files.readString(Path.of(filePath));

        Product[] rawProducts;
        try {
            rawProducts = GSON.fromJson(json, Product[].class);
        } catch (JsonSyntaxException e) {
            throw new IOException("File is not valid JSON: " + filePath, e);
        }

        StoreInventory inventory = new StoreInventory();
        if (rawProducts == null) {
            return inventory;   // file faka chilo
        }

        // Prottek ta ke notun kore constructor diye banai
        for (Product raw : rawProducts) {
            Product product = new Product(
                    raw.getName(),
                    raw.getCategory(),
                    raw.getPrice(),
                    raw.getStockQuantity());
            inventory.addProduct(product);
        }
        return inventory;
    }
}