package com.cjss.eCommerce1.controller;

import com.cjss.eCommerce1.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
public class ReturnController {
    @Autowired
    private ReturnService returnService;
    @PostMapping("/{status}")
    public String initReturn(@RequestParam Integer orderCode, @PathVariable String status){ return returnService.updateReturn(orderCode, status);}
    @PutMapping("/{status}")
    public String updateReturn(@RequestParam Integer orderCode, @PathVariable String status){ return returnService.updateReturn(orderCode, status);}


}
