package com.scda.coffee_shop.domain.model;

public class GreenTea extends Product {
    private static final String CODE = "GR1";

    public GreenTea(String name, double price) {
        super(CODE, name, price);
    }
}