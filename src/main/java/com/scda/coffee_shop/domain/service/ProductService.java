package com.scda.coffee_shop.domain.service;

import com.scda.coffee_shop.domain.model.ProductEnum;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {
    Double NO_VALUE = 0.00;

    public Double applyPriceForBasket(Map<ProductEnum, Integer> products) {
        if (products == null) return NO_VALUE;

        var priceCoffees = coffeeBulkPurchase(products);
        var priceStrawberries = strawberryBulkPurchase(products);
        var priceGreenTeas = greenTeaBuyOneGetOne(products);

        return priceCoffees + priceStrawberries + priceGreenTeas;
    }

    Double greenTeaBuyOneGetOne(Map<ProductEnum, Integer> products) {
        var greenTeas = products.get(ProductEnum.GREEN_TEA);

        if (greenTeas == null || greenTeas < 0) return NO_VALUE;

        if (greenTeas % 2 == 0) {
            return ProductEnum.GREEN_TEA.getPrice() * greenTeas/2;
        } else if (greenTeas == 1) {
            return ProductEnum.GREEN_TEA.getPrice();
        } else {
            return ProductEnum.GREEN_TEA.getPrice() * (greenTeas-1)/2 + ProductEnum.GREEN_TEA.getPrice();
        }
    }

    Double strawberryBulkPurchase(Map<ProductEnum, Integer> products) {
        var strawberries = products.get(ProductEnum.STRAWBERRY);

        if (strawberries == null || strawberries < 0) return NO_VALUE;

        if (strawberries >= 3) {
            var promotionPrice = 4.5;
            return promotionPrice * strawberries;
        } else {
            return ProductEnum.STRAWBERRY.getPrice() * strawberries;
        }
    }

    Double coffeeBulkPurchase(Map<ProductEnum, Integer> products) {
        var coffees = products.get(ProductEnum.COFFEE);

        if (coffees == null || coffees < 0) return NO_VALUE;

        if (coffees >= 3) {
            var result = (ProductEnum.COFFEE.getPrice() * coffees) * 2/3;
            return Math.round(result * 100.0) / 100.0;
        } else {
            return ProductEnum.COFFEE.getPrice() * coffees;
        }
    }
}
