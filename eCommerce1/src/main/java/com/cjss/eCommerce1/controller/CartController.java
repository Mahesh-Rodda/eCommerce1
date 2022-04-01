package com.cjss.eCommerce1.controller;

import com.cjss.eCommerce1.model.CartModel;
import com.cjss.eCommerce1.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addCart(@RequestParam String skuCode,@RequestParam Integer quantity){ return cartService.addCart(skuCode, quantity);}
    @GetMapping("/view")
    public List<CartModel> getCart(){ return  cartService.getCartModel();}

}
