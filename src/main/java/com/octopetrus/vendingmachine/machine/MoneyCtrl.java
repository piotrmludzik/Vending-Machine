package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinType;

import java.util.Map;

public class MoneyCtrl {

    private final Map<Coin, Integer> coinsInStock;
    private double amountOfTakenCoins = 0;

    public MoneyCtrl(Map<Coin, Integer> coinsInStock) {
        if (coinsInStock == null)
            throw new IllegalArgumentException("The money controller of the vending machine cannot work without coins.");

        this.coinsInStock = coinsInStock;
    }

    protected Map<Coin, Integer> getAmountOfCoinsInStock() {  // TODO: should return a coin names and amounts.
        return coinsInStock;
    }

    protected double getAmountOfTakenCoins() {
        return amountOfTakenCoins;
    }

    protected void takeCoin(Coin coin) {  // TODO: should return taken coin! Take coin by value!
        if (CoinType.isUnrecognizedCoin(coin))
            throw new IllegalStateException("Unrecognized coin. Insert a another coin...");

        increaseAmountOfTakenCoins(coin);
        addCoinToStock(coin);
    }

    private void increaseAmountOfTakenCoins(Coin coin) {
        amountOfTakenCoins = amountOfTakenCoins + CoinType.getValue(coin);
    }

    private void addCoinToStock(Coin coin) {
        int actualAmount = 0;
        if (coinsInStock.containsKey(coin))
            actualAmount = coinsInStock.get(coin);

        coinsInStock.put(coin, actualAmount + 1);
    }
}
