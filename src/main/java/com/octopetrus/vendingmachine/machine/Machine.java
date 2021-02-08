package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.products.Product;

import java.util.HashMap;
import java.util.Map;

public class Machine {

    private final Map<Product, Integer> productsInMachine = new HashMap<>();
    private final Map<Coin, Integer> coinsInMachine = new HashMap<>();

    public Machine() {}

    public Machine(Map<Product, Integer> products) {
        productsInMachine.putAll(products);
    }

    public Map<Product, Integer> getProductsInMachine() {
        return productsInMachine;
    }

    public Map<Coin, Integer> getCoinsInMachine() {
        return coinsInMachine;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, int amount) {
        productsInMachine.put(product, amount);
    }

    public void removeProduct(Product product) {
        productsInMachine.remove(product);
    }
}
