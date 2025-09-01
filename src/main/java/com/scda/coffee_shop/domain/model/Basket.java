package com.scda.coffee_shop.domain.model;

import com.scda.coffee_shop.domain.service.ProductService;

import java.util.Map;

public class Basket {
    private final ProductService productService = new ProductService();

    public Map<ProductEnum, Integer> products;
    private Double totalPrice;

    public Basket(Map<ProductEnum, Integer> products) {
        this.products = products;
        this.totalPrice = sumTotalPrice();
    }

    private Double sumTotalPrice() {
        return productService.applyPriceForBasket(products);
    }
}
