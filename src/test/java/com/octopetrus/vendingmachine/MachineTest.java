package com.octopetrus.vendingmachine;

import com.octopetrus.vendingmachine.machine.Machine;
import com.octopetrus.vendingmachine.products.Product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class MachineTest {

    private final Product COLA = new Product("Cola", 1.00);
    private final Product CHIPS = new Product("Chips", 0.50);
    private final Product CANDY = new Product("Candy", 0.65);
    private final Product BUBBLE_GUM = new Product("Bubble gum", 0.25);

    private final Map<Product, Integer> productsInMachine = Map.of(
            COLA, 3,
            CHIPS, 7,
            CANDY, 1);

    private final Machine vendingMachine = new Machine(productsInMachine);

    @Test
    public void addProduct_oneNewProduct_productAddToStock() {
        vendingMachine.addProduct(BUBBLE_GUM, 1);

        Map<Product, Integer> resultOfProductsInMachine = Map.of(
                COLA, 3,
                CHIPS, 7,
                CANDY, 1,
                BUBBLE_GUM, 1);

        assertEquals(resultOfProductsInMachine, vendingMachine.getProductsInMachine());
    }

    @Test
    public void addProduct_sevenPiecesOfExistingProducts_increaseAmountOfProductInStock() {
        vendingMachine.addProduct(COLA, 7);

        Map<Product, Integer> resultOfProductsInMachine = Map.of(
                COLA, 10,
                CHIPS, 7,
                CANDY, 1);

        assertEquals(resultOfProductsInMachine, vendingMachine.getProductsInMachine());
    }

    @Test
    public void removeProduct_existingProduct_decreaseAmountOfProductInStock() {
        vendingMachine.removeProduct(COLA);

        Map<Product, Integer> resultOfProductsInMachine = Map.of(
                COLA, 2,
                CHIPS, 7,
                CANDY, 1);

        assertEquals(resultOfProductsInMachine, vendingMachine.getProductsInMachine());
    }

    @Test
    public void removeProduct_lastPieceOfProductInStock_removeProductFromStock() {
        vendingMachine.removeProduct(CANDY);

        Map<Product, Integer> resultOfProductsInMachine = Map.of(
                COLA, 3,
                CHIPS, 7);

        assertEquals(resultOfProductsInMachine, vendingMachine.getProductsInMachine());
    }
}
