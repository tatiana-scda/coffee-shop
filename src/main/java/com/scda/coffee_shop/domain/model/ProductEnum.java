package com.scda.coffee_shop.domain.model;

public enum ProductEnum {
    GREEN_TEA("GR1", 3.11),
    STRAWBERRY("SR1", 5.00),
    COFFEE("CF1", 11.23);

    private final ProductCode code;
    private final Double price;

    ProductEnum(String codeString, Double price) {
        this.code = new ProductCode(codeString);
        this.price = price;
    }

    public ProductCode getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }
}