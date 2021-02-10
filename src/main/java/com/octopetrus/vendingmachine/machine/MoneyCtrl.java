package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinType;

import java.util.HashMap;
import java.util.Map;

public class MoneyCtrl {

    private final Map<Coin, Integer> coinsInMachine = new HashMap<>();
    private double amountOfTakenCoins = 0;

    protected Map<Coin, Integer> getCoinsInMachine() {
        return coinsInMachine;
    }

    protected void putCoinsIntoMachine(Map<Coin, Integer> coins) {
        if (coins != null)
            coinsInMachine.putAll(coins);
    }

    protected void takeCoin(Coin coin) {
        if (CoinType.isUnrecognizedCoin(coin))
            throw new IllegalStateException("Unrecognized coin. Insert a another coin...");

        increaseAmountOfTakenCoins(coin);
        addCoinToMachine(coin);
    }

    private void increaseAmountOfTakenCoins(Coin coin) {
        amountOfTakenCoins = amountOfTakenCoins + CoinType.getValue(coin);
    }

    private void addCoinToMachine(Coin coin) {
        int actualAmount = 0;
        if (coinsInMachine.containsKey(coin))
            actualAmount = coinsInMachine.get(coin);

        coinsInMachine.put(coin, actualAmount + 1);
    }

    protected double getAmountOfTakenCoins() {
        return amountOfTakenCoins;
    }
}
