package com.octopetrus.vendingmachine;

import com.octopetrus.vendingmachine.coins.Coin;
import com.octopetrus.vendingmachine.coins.CoinFactory;
import com.octopetrus.vendingmachine.coins.CoinTypes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoinFactoryTest {

    private final Coin VALID_COIN = new Coin(5.670, 24.26);  // Quarter dollar
    private final Coin INVALID_COIN = new Coin(5.660, 24.26);

    private final CoinFactory coinFactory = new CoinFactory();

    @Test
    public void getCoinName_validCoin_validName() {
        assertEquals(
                CoinTypes.QUARTER.name,
                coinFactory.getName(VALID_COIN));
    }

    @Test
    public void getCoinValue_validCoin_validValue() {
        assertEquals(
                CoinTypes.QUARTER.value,
                coinFactory.getValue(VALID_COIN));
    }

    @Test
    public void getCoinName_invalidCoin_validName() {
        assertThrows(
                IllegalStateException.class,
                () -> coinFactory.getName(INVALID_COIN));
    }

    @Test
    public void getCoinValue_invalidCoin_validValue() {
        assertThrows(
                IllegalStateException.class,
                () -> coinFactory.getValue(INVALID_COIN));
    }
}
