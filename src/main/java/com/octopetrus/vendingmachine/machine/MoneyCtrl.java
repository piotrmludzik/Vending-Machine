package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinType;

import java.util.HashMap;
import java.util.Map;

public class MoneyCtrl {

    private final Map<Coin, Integer> coinsInMachine = new HashMap<>();
    private double amountOfTakenCoins = 0;
    private Coin takenCoin;

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

        takenCoin = coin;
        increaseAmountOfTakenCoins();
        addCoinToMachine();
    }

    private void increaseAmountOfTakenCoins() {
        amountOfTakenCoins = amountOfTakenCoins + CoinType.getValue(takenCoin);
    }

    private void addCoinToMachine() {
        int actualAmount = 0;
        if (coinsInMachine.containsKey(takenCoin))
            actualAmount = coinsInMachine.get(takenCoin);

        coinsInMachine.put(takenCoin, actualAmount + 1);
    }

    protected double getAmountOfTakenCoins() {
        return amountOfTakenCoins;
    }
}
