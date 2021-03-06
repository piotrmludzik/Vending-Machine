package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinFactory;
import com.octopetrus.vendingmachine.coins.CoinType;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyCtrlTest {

    private final Coin NICKEL = CoinFactory.createCoin(CoinType.NICKEL);    // 0.05$
    private final Coin DIME = CoinFactory.createCoin(CoinType.DIME);        // 0.10$
    private final Coin QUARTER = CoinFactory.createCoin(CoinType.QUARTER);  // 0.25$

    private final Map<Coin, Integer> defaultCoinsInStock =  new HashMap<>() {{
        put(NICKEL, 50);
        put(DIME, 25);
        put(QUARTER, 10);
    }};

    @Test
    public void getAmountOfCoinsInStock_coinsInStock_returnCoinsNameAndAmount() {
        MoneyCtrl moneyCtrl= new MoneyCtrl(defaultCoinsInStock);

        Map<String, Integer> result = new LinkedHashMap<>() {{
                put("Quarter dollar", 10);
                put("Dime", 25);
                put("Nickel", 50);
        }};

        assertIterableEquals(result.entrySet(), moneyCtrl.getAmountOfCoinsInStock().entrySet());
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
        MoneyCtrl moneyCtrl= new MoneyCtrl(defaultCoinsInStock);
        moneyCtrl.takeCoin(QUARTER);

        Map<String, Integer> result = Map.of(
                "Nickel", 50,
                "Dime", 25,
                "Quarter dollar", 11);

        assertEquals(result, moneyCtrl.getAmountOfCoinsInStock());
    }

    @Test
    public void giveChange_enoughMoney_validChange() {
        MoneyCtrl moneyCtrl = new MoneyCtrl(defaultCoinsInStock);
        List<Coin> givenChange = moneyCtrl.giveChange(BigDecimal.valueOf(0.40));

        List<Coin> result = new LinkedList<>(){{
            add(QUARTER);
            add(DIME);
            add(NICKEL);
        }};

        assertIterableEquals(result, givenChange);
    }

    @Test
    public void giveChange_enoughMoney_moneySpentInOneDenomination() {
        MoneyCtrl moneyCtrl = new MoneyCtrl(new HashMap<>() {{
            put(QUARTER, 5);
            put(DIME, 7);
            put(NICKEL, 10);
        }});
        List<Coin> givenChange = moneyCtrl.giveChange(BigDecimal.valueOf(0.20));

        List<Coin> result = new LinkedList<>(){{
            add(DIME);
            add(DIME);
        }};

        assertIterableEquals(result, givenChange);
    }

    @Test
    public void giveChange_notEnoughMoney_IllegalStateException() {
        MoneyCtrl moneyCtrl = new MoneyCtrl(new HashMap<>() {{
            put(QUARTER, 10);
            put(DIME, 20);
        }});

        assertThrows(IllegalStateException.class, () -> moneyCtrl.giveChange(BigDecimal.valueOf(0.05)));
    }
}
