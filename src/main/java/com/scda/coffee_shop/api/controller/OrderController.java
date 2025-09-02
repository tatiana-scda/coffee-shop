package com.scda.coffee_shop.api.controller;

import com.scda.coffee_shop.domain.model.ProductEnum;
import com.scda.coffee_shop.domain.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OrderController {

    private final ProductService productService;

    public OrderController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showForm() {
        return "orderForm";
    }

    @PostMapping("/order")
    public String submitOrder(
            @RequestParam("coffeeCount") int coffeeCount,
            @RequestParam("greenTeaCount") int greenTeaCount,
            @RequestParam("strawberryCount") int strawberryCount,
            Model model
    ) {
        Map<ProductEnum, Integer> products = new HashMap<>();
        products.put(ProductEnum.COFFEE, coffeeCount);
        products.put(ProductEnum.GREEN_TEA, greenTeaCount);
        products.put(ProductEnum.STRAWBERRY, strawberryCount);

        Double total = productService.applyPriceForBasket(products);

        model.addAttribute("coffeeCount", coffeeCount);
        model.addAttribute("greenTeaCount", greenTeaCount);
        model.addAttribute("strawberryCount", strawberryCount);

        model.addAttribute("coffeePrice", ProductEnum.COFFEE.getPrice());
        model.addAttribute("greenTeaPrice", ProductEnum.GREEN_TEA.getPrice());
        model.addAttribute("strawberryPrice", ProductEnum.STRAWBERRY.getPrice());

        model.addAttribute("total", total);

        return "orderResult";
    }
}
