package com.cjss.eCommerce1.controller;

import com.cjss.eCommerce1.service.FulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packing")
public class PackingController {
    @Autowired
    private FulfilmentService fulfilmentService;
    @PostMapping("/accept")
    public String acceptToPacking(@RequestParam Integer orderCode) { return fulfilmentService.acceptToPacking(orderCode); }
    @PutMapping("/update/{status}/{orderCode}")
    public String updatePacking(@PathVariable String status,@PathVariable Integer orderCode) { return  fulfilmentService.updatePacking(status, orderCode);}


    }
