package com.scda.coffee_shop.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ProductCode {

    private final String value;

    public ProductCode(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Product code cannot be null or blank");
        }
        if (!value.matches("[A-Z]{2}\\d")) {
            throw new IllegalArgumentException("Invalid product code format: " + value);
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
