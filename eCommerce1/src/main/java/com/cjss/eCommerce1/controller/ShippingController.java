package com.cjss.eCommerce1.controller;

import com.cjss.eCommerce1.service.FulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
public class ShippingController {
    @Autowired
    private FulfilmentService fulfilmentService;

    @PostMapping("/accept")
    public String acceptToShipping(@RequestParam Integer orderCode) { return  fulfilmentService.acceptToShipping(orderCode);}
    @PutMapping("/update/{status}")
    public String updateShipping(@PathVariable String status,@RequestParam Integer orderCode) { return fulfilmentService.updateShipping(status, orderCode);}


    }
