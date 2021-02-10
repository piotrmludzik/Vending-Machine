package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinType;

import java.util.Map;

public class MoneyCtrl {

    private final Map<Coin, Integer> coinsInMachine;
    private double amountOfTakenCoins = 0;

    public MoneyCtrl(Map<Coin, Integer> coinsInMachine) {
        if (coinsInMachine == null)
            throw new IllegalArgumentException("The money controller of the vending machine cannot work without coins.");

        this.coinsInMachine = coinsInMachine;
    }

    protected Map<Coin, Integer> getCoinsInMachine() {  // TODO: give better name!
        return coinsInMachine;
    }

    protected void takeCoin(Coin coin) {  // TODO: should return taken coin! Take coin by value!
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
