package com.scda.coffee_shop.domain.model;

public class Strawberry extends Product {
    private static final String CODE = "SR1";

    public Strawberry(String name, double price) {
        super(CODE, name, price);
    }
}