package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.products.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductsCtrl {

    private final Map<Product, Integer> productsInMachine = new HashMap<>();

    protected Map<Product, Integer> getProductsInMachine() {
        return productsInMachine;
    }

    protected void putProductsIntoMachine(Map<Product, Integer> products) {
        if (products != null)
            productsInMachine.putAll(products);
    }

    protected void takeProduct(Product product) {
        if (!productsInMachine.containsKey(product))
            throw new IllegalStateException(product.getName() + " not exist in the machine.");

        int actualAmount = productsInMachine.get(product);
        if (actualAmount == 1)  // only one product left
            productsInMachine.remove(product);
        else
            productsInMachine.put(product, actualAmount - 1);
    }
}
