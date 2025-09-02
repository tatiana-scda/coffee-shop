package com.scda.coffee_shop.domain.service;

import com.scda.coffee_shop.domain.model.ProductEnum;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {

    private final ProductService productService = new ProductService();

    @Test
    void applyPriceForBasket_EmptyBasket_ShouldReturnZero() {
        // Given
        Map<ProductEnum, Integer> products = Map.of();

        // When
        var result = productService.applyPriceForBasket(products);

        // Then
        assertEquals(0.0, result, 0.001);
    }

    @Test
    void applyPriceForBasket_NullBasket_ShouldReturnZero() {
        // When
        var result = productService.applyPriceForBasket(null);

        // Then
        assertEquals(0.0, result, 0.001);
    }

    @Test
    void applyPriceForBasket_MixedDiscountedItems_ShouldCalculateCorrectTotal() {
        // Given
        Map<ProductEnum, Integer> products = Map.of(
                ProductEnum.COFFEE, 3,
                ProductEnum.STRAWBERRY, 3,
                ProductEnum.GREEN_TEA, 2
        );

        // When
        var result = productService.applyPriceForBasket(products);

        // Then
        assertEquals(39.07, result, 0.001);
    }

    @Test
    void applyPriceForBasket_MaximumItems_ShouldCalculateCorrectTotal() {
        // Given
        Map<ProductEnum, Integer> products = Map.of(
                ProductEnum.COFFEE, Integer.MAX_VALUE,
                ProductEnum.STRAWBERRY, Integer.MAX_VALUE,
                ProductEnum.GREEN_TEA, Integer.MAX_VALUE
        );

        // When
        var result = productService.applyPriceForBasket(products);

        // Then
        assertEquals(2.908050772135E10, result, 0.001);
    }

    @Test
    void applyPriceForBasket_NegativeItems_ShouldCalculateCorrectTotal() {
        // Given
        Map<ProductEnum, Integer> products = Map.of(
                ProductEnum.COFFEE, -10,
                ProductEnum.STRAWBERRY, -1,
                ProductEnum.GREEN_TEA, Integer.MIN_VALUE
        );

        // When
        var result = productService.applyPriceForBasket(products);

        // Then
        assertEquals(0.00, result, 0.001);
    }

    @Test
    void applyPriceForBasket_WithZeroQuantities_ShouldCalculateCorrectly() {
        // Given
        Map<ProductEnum, Integer> products = Map.of(
                ProductEnum.COFFEE, 0,
                ProductEnum.STRAWBERRY, 0,
                ProductEnum.GREEN_TEA, 0
        );

        // When
        var result = productService.applyPriceForBasket(products);

        // Then
        assertEquals(0.0, result, 0.001);
    }

    @Test
    void coffee_IfHasMoreThan3Items_ShouldReturnCorrectly() {
        // Given
        Map<ProductEnum, Integer> map = Map.of(ProductEnum.COFFEE, 56);

        // When
        var result = productService.coffeeBulkPurchase(map);

        // Then
        assertEquals(419.25, result, 0.001);
    }

    @Test
    void coffee_IfHasLessThan3Items_ShouldReturnRegularPrice() {
        // Given
        Map<ProductEnum, Integer> map = Map.of(ProductEnum.COFFEE, 2);

        // When
        var result = productService.coffeeBulkPurchase(map);

        // Then
        assertEquals(22.46, result, 0.001);
    }

    @Test
    void coffee_IfHasExactly3Items_ShouldApplyDiscount() {
        // Given
        Map<ProductEnum, Integer> map = Map.of(ProductEnum.COFFEE, 3);

        // When
        var result = productService.coffeeBulkPurchase(map);

        // Then
        assertEquals(22.46, result, 0.001);
    }

    @Test
    void coffee_IfHasZeroItems_ShouldReturnZero() {
        // Given
        Map<ProductEnum, Integer> map = Map.of(ProductEnum.COFFEE, 0);

        // When
        var result = productService.coffeeBulkPurchase(map);

        // Then
        assertEquals(0, result, 0.001);
    }

    @Test
    void coffee_IfHasNullItems_ShouldReturnZero() {
        // Given
        Map<ProductEnum, Integer> map = Map.of();

        // When
        var result = productService.coffeeBulkPurchase(map);

        // Then
        assertEquals(0, result, 0.001);
    }

    @Test
    void strawberry_IfHasLessThan3Items_ShouldReturnRegularPrice() {
        // Given
        Map<ProductEnum, Integer> map = Map.of(ProductEnum.STRAWBERRY, 2);

        // When
        var result = productService.strawberryBulkPurchase(map);

        // Then
        assertEquals(10.0, result, 0.001);
    }

    @Test
    void strawberry_IfHasExactly3Items_ShouldApplyDiscount() {
        // Given
        Map<ProductEnum, Integer> map = Map.of(ProductEnum.STRAWBERRY, 3);

        // When
        var result = productService.strawberryBulkPurchase(map);

        // Then
        assertEquals(13.5, result, 0.001);
    }

    @Test
    void strawberry_IfHasMoreThan3Items_ShouldApplyDiscount() {
        // Given
        Map<ProductEnum, Integer> map = Map.of(ProductEnum.STRAWBERRY, 5);

        // When
        var result = productService.strawberryBulkPurchase(map);

        // Then
        assertEquals(22.5, result, 0.001);
    }

    @Test
    void greenTea_IfHasEvenItems_ShouldReturnCorrectly() {
        // Given
        Map<ProductEnum, Integer> map = Map.of(ProductEnum.GREEN_TEA, 44);

        // When
        var result = productService.greenTeaBuyOneGetOne(map);

        // Then
        assertEquals(68.42, result, 0.001);
    }

    @Test
    void greenTea_IfHasOddItems_ShouldReturnCorrectly() {
        // Given
        Map<ProductEnum, Integer> map = Map.of(ProductEnum.GREEN_TEA, 51);

        // When
        var result = productService.greenTeaBuyOneGetOne(map);

        // Then
        assertEquals(80.86, result, 0.001);
    }
}
