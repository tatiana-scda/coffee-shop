package com.scda.coffee_shop.domain.model;

public class Coffee extends Product {
    private static final String CODE = "CF1";

    public Coffee(String name, double price) {
        super(CODE, name, price);
    }
}
