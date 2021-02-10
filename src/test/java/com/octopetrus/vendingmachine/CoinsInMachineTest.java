package com.octopetrus.vendingmachine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinFactory;
import com.octopetrus.vendingmachine.coins.CoinType;
import com.octopetrus.vendingmachine.machine.Machine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class CoinsInMachineTest {

    private final Coin NICKEL = CoinFactory.createCoin(CoinType.NICKEL);    // 0.05$
    private final Coin DIME = CoinFactory.createCoin(CoinType.DIME);        // 0.10$
    private final Coin QUARTER = CoinFactory.createCoin(CoinType.QUARTER);  // 0.25$

    private final Map<Coin, Integer> coinsInMachine = Map.of(
            NICKEL, 50,
            DIME, 25,
            QUARTER, 10
    );

    private final Machine vendingMachine = new Machine(null, coinsInMachine);

    @Test
    public void takeCoin_validCoin_increaseAmountOfTakenCoin() {
        vendingMachine.takeCoin(QUARTER);

        assertEquals(0.25, vendingMachine.getAmountOfTakenCoins());
    }

    @Test
    public void takeCoin_validCoinNotExistInMachine_addCoinToMachine() {
        Machine vMachine = new Machine();
        vMachine.takeCoin(QUARTER);

        Map<Coin, Integer> resultOfCoinsInMachine = Map.of(QUARTER, 1);

        assertEquals(resultOfCoinsInMachine, vMachine.getCoinsInMachine());
    }

    @Test
    public void takeCoin_validCoinExistInMachine_addCoinToMachine() {
        vendingMachine.takeCoin(QUARTER);

        Map<Coin, Integer> resultOfCoinsInMachine = Map.of(
                NICKEL, 50,
                DIME, 25,
                QUARTER, 11
        );

        assertEquals(resultOfCoinsInMachine, vendingMachine.getCoinsInMachine());
    }
}
