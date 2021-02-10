package com.octopetrus.vendingmachine.products;

public class Product {

    private final int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        if (name == null || name.equals("") || price <= 0)
            throw new IllegalArgumentException("A product should have name and price greater than zero.");

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
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
