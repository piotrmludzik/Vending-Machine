package com.octopetrus.vendingmachine.machine;

import com.octopetrus.vendingmachine.products.Product;

import java.util.Map;

public class ProductsCtrl {

    private final Map<Product, Integer> productsInStock;

    public ProductsCtrl(Map<Product, Integer> productsInStock) {
        if (productsInStock == null)
            throw new IllegalArgumentException("The products controller of the vending machine cannot work without products.");

        this.productsInStock = productsInStock;
    }

    protected Map<Product, Integer> getProductsInStock() {  // TODO: should return a product names and amounts.
        return productsInStock;
    }

    protected Product takeProduct(int productId) {
        Product product = getProductById(productId);
        removeProductFromStock(product);

        return product;
    }

    private Product getProductById(int productId) {
        Product productById;
        for (Product product : productsInStock.keySet())
            if (product.getId() == productId)
                return product;

        throw new IllegalStateException("There is no product with the given " + productId + " id.");
    }

    private void removeProductFromStock(Product product) {
        int actualAmount = productsInStock.get(product);
        if (actualAmount == 1)  // only one product left
            productsInStock.remove(product);
        else
            productsInStock.put(product, actualAmount - 1);
    }
}
