package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.products.Product;

import java.util.Map;

public class Machine {

    private final ProductsCtrl productsCtrl = new ProductsCtrl();
    private final MoneyCtrl moneyCtrl = new MoneyCtrl();

    public Machine(Map<Product, Integer> products, Map<Coin, Integer> coins) {
        productsCtrl.putProductsIntoMachine(products);
        moneyCtrl.putCoinsIntoMachine(coins);
    }
}
