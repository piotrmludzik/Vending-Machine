package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinComparator;
import com.octopetrus.vendingmachine.coins.CoinType;

import java.math.BigDecimal;
import java.util.*;

public class MoneyCtrl {

    private final TreeMap<Coin, Integer> coinsInStock = new TreeMap<>(new CoinComparator(new HashMap<>()));
    private BigDecimal amountOfTakenCoins = BigDecimal.ZERO;

    protected MoneyCtrl(Map<Coin, Integer> coinsInStock) {
        if (coinsInStock == null)
            throw new IllegalArgumentException("The money controller of the vending machine cannot work without coins.");

        this.coinsInStock.putAll(coinsInStock);
    }

    protected Map<String, Integer> getAmountOfCoinsInStock() {
        Map<String, Integer> coins = new LinkedHashMap<>();
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
        increaseCoin(coinsInStock, coin);
    }

    private void increaseAmountOfTakenCoins(Coin coin) {
        amountOfTakenCoins = amountOfTakenCoins.add(CoinType.getValue(coin));
    }

    protected List<Coin> giveChange(BigDecimal changeAmount) {
        List<Coin> changeToGive = new LinkedList<>();
        for (Map.Entry<Coin, Integer> e : coinsInStock.entrySet()) {  // iterate by coin types
            Coin coin = e.getKey();

            for (int n=1; n <= e.getValue(); n++) {  // iterate by coin amount
                if (isToBigValueToSpendChange(changeAmount, coin))
                    break;  // go to the next coin type

                moveCoinFromStockToChangeToGive(changeToGive, coin);
                changeAmount = changeAmount.subtract(CoinType.getValue(coin));

                if (isChangeAmountIsZero(changeAmount)) {
                    amountOfTakenCoins = BigDecimal.ZERO;
                    return changeToGive;
                }
            }
        }

        throw new IllegalStateException("Could not spend the change.");
    }

    private boolean isToBigValueToSpendChange(BigDecimal changeAmount, Coin coin) {
        BigDecimal coinValue = CoinType.getValue(coin);
        BigDecimal predictedChangeAmount = changeAmount.subtract(coinValue);
        return predictedChangeAmount.compareTo(BigDecimal.ZERO) < 0;
    }

    private void moveCoinFromStockToChangeToGive(List<Coin> changeToGive, Coin coin) {
        changeToGive.add(coin);
        decreaseCoin(coinsInStock, coin);
    }

    private boolean isChangeAmountIsZero(BigDecimal changeAmount) {
        return changeAmount.compareTo(BigDecimal.ZERO) == 0;
    }

    private void increaseCoin(Map<Coin, Integer> stock, Coin coin) {
        int count = stock.getOrDefault(coin, 0);
        stock.put(coin, count + 1);
    }

    private void decreaseCoin(Map<Coin, Integer> stock, Coin coin) {
        if (!stock.containsKey(coin))
            throw new IllegalStateException("Cannot decrease a coin that is not in stock.");

        int count = stock.get(coin);
        if (count == 1)
            stock.remove(coin);
        else
            stock.put(coin, count - 1);
    }
}
