package com.octopetrus.vendingmachine.coins;

public class CoinFactory {

    public static Coin createCoin(CoinType coinType) {
        return new Coin(coinType.weight, coinType.size);
    }

    private CoinFactory() {
        throw new AssertionError("The CoinFactory class cannot be an object.");
    }
}
