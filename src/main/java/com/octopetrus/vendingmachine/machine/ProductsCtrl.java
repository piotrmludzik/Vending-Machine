package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.products.Product;

import java.util.Map;

public class ProductsCtrl {

    private final Map<Product, Integer> productsInMachine;

    public ProductsCtrl(Map<Product, Integer> productsInMachine) {
        if (productsInMachine == null)
            throw new IllegalArgumentException("The products controller of the vending machine cannot work without products.");

        this.productsInMachine = productsInMachine;
    }

    protected Map<Product, Integer> getProductsInMachine() {  // TODO: give better name!
        return productsInMachine;
    }

    protected void takeProduct(Product product) {  // TODO: should return taken product! Take product by ID!
        if (!productsInMachine.containsKey(product))
            throw new IllegalStateException(product.getName() + " not exist in the machine.");

        int actualAmount = productsInMachine.get(product);
        if (actualAmount == 1)  // only one product left
            productsInMachine.remove(product);
        else
            productsInMachine.put(product, actualAmount - 1);
    }
}
