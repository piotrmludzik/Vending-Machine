package com.octopetrus.vendingmachine.coins;

// TODO: refactor monetary system from double to BigDecimal!
public class Coin {

    public final double weight;
    public final double size;

    public Coin(double weight, double size) {
        if (weight <= 0 || size <= 0)
            throw new IllegalArgumentException("A coin should have weight and size greater than zero.");

        this.weight = weight;
        this.size = size;
    }

    @Override
    public String toString() {
        return CoinType.getValue(this) + "$";
    }
}
