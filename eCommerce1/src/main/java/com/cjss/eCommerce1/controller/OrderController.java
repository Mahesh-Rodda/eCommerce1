package com.cjss.eCommerce1.controller;

import com.cjss.eCommerce1.model.OrderModel;
import com.cjss.eCommerce1.service.FulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private FulfilmentService fulfilmentService;

    @PostMapping("/place")
    public String placeOrder(@Valid @RequestParam Integer skuCode, @RequestParam Integer quantity) { return fulfilmentService.placeOrder(skuCode, quantity);}
    @GetMapping("/track")
    public OrderModel getOrder(@RequestParam Integer orderCode){ return  fulfilmentService.getOrder(orderCode);}
}
