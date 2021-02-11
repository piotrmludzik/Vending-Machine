package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MoneyCtrl {

    private final Map<Coin, Integer> coinsInStock;
    private BigDecimal amountOfTakenCoins = BigDecimal.ONE;

    protected MoneyCtrl(Map<Coin, Integer> coinsInStock) {
        if (coinsInStock == null)
            throw new IllegalArgumentException("The money controller of the vending machine cannot work without coins.");

        this.coinsInStock = coinsInStock;
    }

    protected Map<String, Integer> getAmountOfCoinsInStock() {
        Map<String, Integer> coins = new HashMap<>();
        for (Map.Entry<Coin, Integer> e : coinsInStock.entrySet()) {
            String coinName = CoinType.getName(e.getKey());
            int coinAmount = e.getValue();
            coins.put(coinName, coinAmount);
        }

        return coins;
    }

    protected BigDecimal getAmountOfTakenCoins() {
        return amountOfTakenCoins;
    }

    protected void takeCoin(Coin coin) {
        if (CoinType.isUnrecognizedCoin(coin))
            throw new IllegalStateException("Unrecognized coin. Insert a another coin...");

        increaseAmountOfTakenCoins(coin);
        addCoinToStock(coin);
    }

    private void increaseAmountOfTakenCoins(Coin coin) {
        amountOfTakenCoins = amountOfTakenCoins.add(CoinType.getValue(coin));
    }

    private void addCoinToStock(Coin coin) {
        int actualAmount = 0;
        if (coinsInStock.containsKey(coin))
            actualAmount = coinsInStock.get(coin);

        coinsInStock.put(coin, actualAmount + 1);
    }
}
