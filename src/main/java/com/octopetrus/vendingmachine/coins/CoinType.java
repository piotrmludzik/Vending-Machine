package com.octopetrus.vendingmachine.coins;

public enum CoinType {

    CENT ("Cent", 0.01, 2.500, 19.05),
    NICKEL ("Nickel", 0.05, 5.000, 21.21),
    DIME ("Dime", 0.10, 2.268, 17.91),
    QUARTER ("Quarter dollar", 0.25, 5.670, 24.26),
    HALF ("Half dollar", 0.50, 11.340, 30.61),
    DOLLAR ("Dollar", 1.00, 8.1, 26.49);

    public final String name;
    public final double value;
    public final double weight;
    public final double size;

    CoinType(String name, double value, double weight, double size) {
        this.name = name;
        this.value = value;
        this.weight = weight;
        this.size = size;
    }
}
