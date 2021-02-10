package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.products.Product;

import java.util.Map;

public class Machine {

    private final ProductsCtrl productsCtrl;
    private final MoneyCtrl moneyCtrl;

    public Machine(Map<Product, Integer> products, Map<Coin, Integer> coins) {
        this.productsCtrl = new ProductsCtrl(products);
        this.moneyCtrl = new MoneyCtrl(coins);
    }
}
