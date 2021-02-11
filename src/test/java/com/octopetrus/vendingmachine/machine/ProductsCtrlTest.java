package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.products.Product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class ProductsCtrlTest {

    private final Product COLA = new Product(1, "Cola", 1.00);
    private final Product CHIPS = new Product(2, "Chips", 0.50);
    private final Product CANDY = new Product(3, "Candy", 0.65);

    private final Map<Product, Integer> defaultProductsInStock = new HashMap<>() {{
        put(COLA, 3);
        put(CHIPS, 7);
        put(CANDY, 1);
    }};

    @Test
    public void getAmountOfProductsInStock_productsInStock_returnProductsNameAndAmount() {
        ProductsCtrl productsCtrl = new ProductsCtrl(defaultProductsInStock);

        Map<String, Integer> result = Map.of(
                "Cola", 3,
                "Chips", 7,
                "Candy", 1);

        assertEquals(result, productsCtrl.getAmountOfProductsInStock());
    }

    @Test
    public void getAmountOfProductsInStock_emptyStock_returnNull() {
        ProductsCtrl productsCtrl = new ProductsCtrl(new HashMap<>());

        Map<String, Integer> result = new HashMap<>();  // empty Map

        assertEquals(result, productsCtrl.getAmountOfProductsInStock());
    }

    @Test
    public void takeProduct_productByIdInStock_returnProduct() {
        ProductsCtrl productsCtrl = new ProductsCtrl(defaultProductsInStock);
        Product product = productsCtrl.takeProduct(1);

        Product result = new Product(1, "Cola", 1.00);

        assertEquals(result, product);
    }

    @Test
    public void takeProduct_productByIdInStock_removeProductFromStock() {
        ProductsCtrl productsCtrl = new ProductsCtrl(defaultProductsInStock);
        productsCtrl.takeProduct(3);  // last one in stock

        Map<String, Integer> result = Map.of(
            "Cola", 3,
            "Chips", 7
        );

        assertEquals(result, productsCtrl.getAmountOfProductsInStock());
    }

    @Test
    public void takeProduct_noProductByIdInStock_throwIllegalStateException() {
        ProductsCtrl productsCtrl = new ProductsCtrl(defaultProductsInStock);

        assertThrows(IllegalStateException.class, () -> productsCtrl.takeProduct(7));  // non-existent product
    }
}
