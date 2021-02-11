package com.octopetrus.vendingmachine.coins;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;

public class CoinComparator implements Comparator<Coin> {

    public CoinComparator(Map<Coin, Integer> base) {
    }

    @Override
    public int compare(Coin c1, Coin c2) {
        BigDecimal c1Value = CoinType.getValue(c1);
        BigDecimal c2Value = CoinType.getValue(c2);

        int result = c1Value.compareTo(c2Value);
        switch (result) {
            case 1:
                return -1;
            case -1:
                return 1;
            default:
                return 0;
        }
    }
}
