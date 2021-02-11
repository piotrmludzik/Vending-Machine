package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinFactory;
import com.octopetrus.vendingmachine.coins.CoinType;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyCtrlTest {

    private final Coin NICKEL = CoinFactory.createCoin(CoinType.NICKEL);    // 0.05$
    private final Coin DIME = CoinFactory.createCoin(CoinType.DIME);        // 0.10$
    private final Coin QUARTER = CoinFactory.createCoin(CoinType.QUARTER);  // 0.25$

    private final Map<Coin, Integer> coinsInStock =  new HashMap<>() {{
        put(NICKEL, 50);
        put(DIME, 25);
        put(QUARTER, 10);
    }};

    @Test
    public void getAmountOfCoinsInStock_coinsInStock_returnCoinsNameAndAmount() {
        MoneyCtrl moneyCtrl= new MoneyCtrl(coinsInStock);

        Map<String, Integer> result = Map.of(
                "Nickel", 50,
                "Dime", 25,
                "Quarter dollar", 10);

        assertEquals(result, moneyCtrl.getAmountOfCoinsInStock());
    }

    @Test
    public void getAmountOfCoinsInStock_emptyStock_returnNull() {
        MoneyCtrl moneyCtrl = new MoneyCtrl(new HashMap<>());

        Map<String, Integer> result = new HashMap<>();  // empty Map

        assertEquals(result, moneyCtrl.getAmountOfCoinsInStock());
    }

    @Test
    public void takeCoin_validCoin_increaseAmountOfTakenCoin() {
        MoneyCtrl moneyCtrl = new MoneyCtrl(new HashMap<>());
        moneyCtrl.takeCoin(QUARTER);

        BigDecimal result = BigDecimal.valueOf(0.25);

        assertEquals(result, moneyCtrl.getAmountOfTakenCoins());
    }

    @Test
    public void takeCoin_validCoinNotExistInMachine_addCoinToMachine() {
        MoneyCtrl moneyCtrl = new MoneyCtrl(new HashMap<>() {{
            put(NICKEL, 50);
            put(DIME, 25);
        }});
        moneyCtrl.takeCoin(QUARTER);

        Map<String, Integer> result = Map.of(
                "Nickel", 50,
                "Dime", 25,
                "Quarter dollar", 1);

        assertEquals(result, moneyCtrl.getAmountOfCoinsInStock());
    }

    @Test
    public void takeCoin_validCoinExistInMachine_addCoinToMachine() {
        MoneyCtrl moneyCtrl= new MoneyCtrl(coinsInStock);
        moneyCtrl.takeCoin(QUARTER);

        Map<String, Integer> result = Map.of(
                "Nickel", 50,
                "Dime", 25,
                "Quarter dollar", 11);

        assertEquals(result, moneyCtrl.getAmountOfCoinsInStock());
    }
}
