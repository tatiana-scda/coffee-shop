package com.scda.coffee_shop.api.controller;

import com.scda.coffee_shop.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showForm_ReturnsOrderForm() {
        String viewName = orderController.showForm();
        assertEquals("orderForm", viewName, "GET / should return 'orderForm' view");
    }

    @Test
    void submitOrder_AddsAttributesAndReturnsOrderResult() {
        // Given
        int coffeeCount = 2;
        int greenTeaCount = 1;
        int strawberryCount = 3;

        double expectedTotal = 25.0;
        when(productService.applyPriceForBasket(any(Map.class))).thenReturn(expectedTotal);

        // When
        String viewName = orderController.submitOrder(coffeeCount, greenTeaCount, strawberryCount, model);

        // Then
        assertEquals("orderResult", viewName, "POST /order should return 'orderResult' view");
        verify(productService, times(1)).applyPriceForBasket(any(Map.class));

        verify(model).addAttribute("coffeeCount", coffeeCount);
        verify(model).addAttribute("greenTeaCount", greenTeaCount);
        verify(model).addAttribute("strawberryCount", strawberryCount);
        verify(model).addAttribute("total", expectedTotal);
    }
}
