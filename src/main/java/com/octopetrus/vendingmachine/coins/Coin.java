package com.octopetrus.vendingmachine.coins;

public class Coin {

    public final double weight;
    public final double size;

    public Coin(double weight, double size) {
        this.weight = weight;
        this.size = size;
    }

    @Override
    public String toString() {
        return new CoinFactory().getValue(this) + "$";
    }
}
