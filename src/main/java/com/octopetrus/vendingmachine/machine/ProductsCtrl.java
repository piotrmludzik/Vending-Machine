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

    protected Product takeProduct(int productId) {
        Product product = getProductById(productId);
        removeProductFromMachine(product);

        return product;
    }

    private Product getProductById(int productId) {
        Product productById;
        for (Product product : productsInMachine.keySet())
            if (product.getId() == productId)
                return product;

        throw new IllegalStateException("There is no product with the given " + productId + " id.");
    }

    private void removeProductFromMachine(Product product) {
        int actualAmount = productsInMachine.get(product);
        if (actualAmount == 1)  // only one product left
            productsInMachine.remove(product);
        else
            productsInMachine.put(product, actualAmount - 1);
    }
}
