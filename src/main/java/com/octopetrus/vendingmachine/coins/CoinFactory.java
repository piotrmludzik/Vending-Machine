package com.octopetrus.vendingmachine.coins;

public class CoinFactory {

    public String getName(Coin coin) {
        for (CoinTypes coinType : CoinTypes.values()) {
            if (coinType.weight == coin.weight && coinType.size == coin.size)
                return coinType.name;
        }

        throw new IllegalStateException("Not know the coin name with given " + getCoinProperties(coin) + ".");
    }

    public double getValue(Coin coin) {
        for (CoinTypes coinType : CoinTypes.values()) {
            if (coinType.weight == coin.weight && coinType.size == coin.size)
                return coinType.value;
        }

        throw new IllegalStateException("Not know the coin value with given " + getCoinProperties(coin) + ".");
    }

    private String getCoinProperties(Coin coin) {
        return String.format("weight (%s g) and size (%s mmm)", coin.weight, coin.size);
    }
}
