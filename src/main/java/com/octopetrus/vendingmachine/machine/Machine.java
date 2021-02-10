package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinType;
import com.octopetrus.vendingmachine.products.Product;

import java.util.HashMap;
import java.util.Map;

public class Machine {

    private final Map<Product, Integer> productsInMachine = new HashMap<>();
    private final Map<Coin, Integer> coinsInMachine = new HashMap<>();
    private double amountOfTakenCoins = 0;
    private Coin takenCoin;

    public Machine() {}

    public Machine(Map<Product, Integer> products, Map<Coin, Integer> coins) {
        productsInMachine.putAll(products);
        coinsInMachine.putAll(coins);
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
        int actualAmount = 0;
        if (productsInMachine.containsKey(product))
            actualAmount = productsInMachine.get(product);

        productsInMachine.put(product, amount + actualAmount);
    }

    public void removeProduct(Product product) {
        if (!productsInMachine.containsKey(product))
            throw new IllegalStateException(product.getName() + " not exist in the machine. It has not been removed.");

        int actualAmount = productsInMachine.get(product);
        if (actualAmount == 1)  // only one product left
            productsInMachine.remove(product);
        else
            productsInMachine.put(product, actualAmount - 1);
    }

    public void takeCoin(Coin coin) {
        if (CoinType.isUnrecognizedCoin(coin))
            throw new IllegalStateException("Unrecognized coin. Insert a another coin...");

        takenCoin = coin;
        increaseAmountOfTakenCoins();
        addCoinToMachine();
    }

    private void increaseAmountOfTakenCoins() {
        amountOfTakenCoins = amountOfTakenCoins + CoinType.getValue(takenCoin);
    }

    private void addCoinToMachine() {
        int actualAmount = coinsInMachine.get(takenCoin);
        coinsInMachine.put(takenCoin, actualAmount + 1);
    }

    public double getAmountOfTakenCoins() {
        return amountOfTakenCoins;
    }
}
