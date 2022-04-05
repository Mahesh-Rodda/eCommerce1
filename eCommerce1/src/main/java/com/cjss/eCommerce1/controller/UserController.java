package com.cjss.eCommerce1.controller;

import com.cjss.eCommerce1.model.CartModel;
import com.cjss.eCommerce1.model.OrderModel;
import com.cjss.eCommerce1.service.CartService;
import com.cjss.eCommerce1.service.FulfilmentService;
import com.cjss.eCommerce1.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CartService cartService;
    @Autowired
    private FulfilmentService fulfilmentService;
    @Autowired
    private ReturnService returnService;
    @PostMapping("/return")
    public String initReturn(@RequestParam Integer orderCode){ return returnService.updateReturn(orderCode, "Return");}
    @PostMapping("/order")
    public String placeOrder(@Valid @RequestParam Integer skuCode, @RequestParam Integer quantity) { return fulfilmentService.placeOrder(skuCode, quantity);}
    @GetMapping("/order/track")
    public OrderModel getOrder(@RequestParam Integer orderCode){ return  fulfilmentService.getOrder(orderCode);}
    @PostMapping("/cart/add")
    public String addCart(@RequestParam Integer skuCode,@RequestParam Integer quantity){ return cartService.addCart(skuCode, quantity);}
    @GetMapping("/cart/view")
    public List<CartModel> getCart(){ return  cartService.getCartModel();}

}
