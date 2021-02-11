package com.octopetrus.vendingmachine.products;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return name;
    }
}
