package com.octopetrus.vendingmachine.coins;

public class CoinFactory {

    public Coin createCoin(CoinType coinType) {
        return new Coin(coinType.weight, coinType.size);
    }
}
