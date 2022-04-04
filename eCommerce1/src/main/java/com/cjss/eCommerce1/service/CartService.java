package com.cjss.eCommerce1.service;

import com.cjss.eCommerce1.entity.CartEntity;
import com.cjss.eCommerce1.entity.InventoryEntity;
import com.cjss.eCommerce1.entity.SKUEntity;
import com.cjss.eCommerce1.model.CartModel;
import com.cjss.eCommerce1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SKURepo skuRepo;
    @Autowired
    private PriceRepo priceRepo;
    @Autowired
    private InventoryRepo inventoryRepo;


    public String addCart(Integer skuCode,Integer quantity){
        Optional<SKUEntity> skuEntity =skuRepo.findById(skuCode);
        if (skuEntity.isPresent()){
           InventoryEntity inventoryEntity = inventoryRepo.findById(skuCode).get();
           if(inventoryEntity.getQuantity()>=quantity) {
               CartEntity cartEntity = new CartEntity(quantity,skuEntity.get());
               String productName =productRepo.findById(skuEntity.get().getProductEntity().getProductCode()).get().getProductName();
               cartRepo.save(cartEntity);
               return "PRODUCT NAME : "+productName+"\n SIZE : "+skuEntity.get().getSkuSize()+"\n NO.OF : "+quantity+"\n IS ADDED TO CART SUCCESSFULLY";
           }

           return "OUT OF STACK";
    }
        return "SKU CODE IS NOT EXISTS";
    }
    public List<CartModel> getCartModel(){
        List<CartEntity> cartEntities = cartRepo.findAll();
        Double[] price={0.0};
        List<CartModel> cartModels =cartEntities.stream().map(cE -> {
             price[0] =price[0]+priceRepo.findById(cE.getSkuEntity().getSkuCode()).get().getPrice()*cE.getQuantity();
            CartModel cartModel = new  CartModel(
                cE.getCartCode(),
                cE.getSkuEntity().getProductEntity().getProductName(),
                cE.getSkuEntity().getSkuSize(),
                priceRepo.findById(cE.getSkuEntity().getSkuCode()).get().getPrice(),
                (priceRepo.findById(cE.getSkuEntity().getSkuCode()).get().getPrice())* cE.getQuantity(),
                cE.getQuantity(),
                inventoryRepo.findById(cE.getSkuEntity().getSkuCode()).get().getQuantity(),
                price[0]);
        return cartModel;}).collect(Collectors.toList());
        return cartModels;
    }
}
