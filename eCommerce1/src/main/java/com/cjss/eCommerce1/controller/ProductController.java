package com.cjss.eCommerce1.controller;

import com.cjss.eCommerce1.model.ProductModel;
import com.cjss.eCommerce1.model.SKUModel;
import com.cjss.eCommerce1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public String addProduct(@Valid @RequestBody ProductModel productModel){return productService.addProduct(productModel);}
    @PostMapping("/add/sku")
    public String addSku(@RequestBody SKUModel skuModel){return productService.addProductSKU(skuModel);}
    @PutMapping("/update")
    public String updateProduct(@RequestBody ProductModel productModel){ return  productService.updateProduct(productModel);}
    @PutMapping("/update/sku")
    public String updateSku(@RequestBody SKUModel skuModel){ return  productService.updateSKU(skuModel);}
}
