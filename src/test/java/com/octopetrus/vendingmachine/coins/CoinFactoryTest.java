package com.octopetrus.vendingmachine.coins;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoinFactoryTest {

    private final Coin VALID_COIN = new Coin(5.670, 24.26);  // Quarter dollar
    private final Coin INVALID_COIN = new Coin(5.660, 24.26);

    @Test
    public void getCoinName_validCoin_validName() {
        assertEquals(
                CoinType.QUARTER.name,
                CoinType.getName(VALID_COIN));
    }

    @Test
    public void getCoinValue_validCoin_validValue() {
        assertEquals(
                CoinType.QUARTER.value,
                CoinType.getValue(VALID_COIN));
    }

    @Test
    public void getCoinName_invalidCoin_IllegalStateException() {
        assertThrows(
                IllegalStateException.class,
                () -> CoinType.getName(INVALID_COIN));
    }

    @Test
    public void getCoinValue_invalidCoin_IllegalStateException() {
        assertThrows(
                IllegalStateException.class,
                () -> CoinType.getValue(INVALID_COIN));
    }
}
