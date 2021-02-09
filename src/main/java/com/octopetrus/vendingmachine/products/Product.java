package com.octopetrus.vendingmachine.products;

public class Product {

    private String name;
    private double price;

    public Product(String name, double price) {
        if (name == null || name.equals("") || price <= 0)
            throw new IllegalArgumentException("A product should have name and price greater than zero.");

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
