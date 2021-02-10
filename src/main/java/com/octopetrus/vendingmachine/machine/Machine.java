package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.products.Product;

import java.util.Map;

public class Machine {

    private final ProductsCtrl productsCtrl = new ProductsCtrl();
    private final MoneyCtrl moneyCtrl = new MoneyCtrl();

    public Machine() {}

    public Machine(Map<Product, Integer> products, Map<Coin, Integer> coins) {
        productsCtrl.putProductsIntoMachine(products);
        moneyCtrl.putCoinsIntoMachine(coins);
    }

    public Map<Coin, Integer> getCoinsInMachine() {
        return moneyCtrl.getCoinsInMachine();
    }

    public void takeCoin(Coin coin) {
        moneyCtrl.takeCoin(coin);
    }

    public double getAmountOfTakenCoins() {
        return moneyCtrl.getAmountOfTakenCoins();
    }

    public Map<Product, Integer> getProductsInMachine() {
        return productsCtrl.getProductsInMachine();
    }

    public void addProduct(Product product) {  // TODO: this is a redundant function!
        productsCtrl.addProduct(product, 1);
    }
    public void addProduct(Product product, int amount) {  // TODO: this is a redundant function!
        productsCtrl.addProduct(product, amount);
    }

    public void takeProduct(Product product) {
        productsCtrl.takeProduct(product);
    }
}
