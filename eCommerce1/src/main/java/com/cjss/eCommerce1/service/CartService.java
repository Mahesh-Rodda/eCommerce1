package com.cjss.eCommerce1.service;

import com.cjss.eCommerce1.configuration.MyUserDetailsService;
import com.cjss.eCommerce1.entity.CartEntity;
import com.cjss.eCommerce1.entity.InventoryEntity;
import com.cjss.eCommerce1.entity.SKUEntity;
import com.cjss.eCommerce1.entity.UserEntity;
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
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MyUserDetailsService myUserDetailsService;


    public String addCart(Integer skuCode,Integer quantity){
        Optional<UserEntity> userEntity = userRepo.findById(myUserDetailsService.id);
        Optional<SKUEntity> skuEntity =skuRepo.findById(skuCode);
        if (skuEntity.isPresent()){
           Optional<InventoryEntity> inventoryEntity = inventoryRepo.findById(skuCode);
            Optional<CartEntity> cartRepoBySkuEntity = cartRepo.findBySkuEntity(skuEntity.get());
            if (cartRepoBySkuEntity.isPresent())
            {
                cartRepoBySkuEntity.get().setQuantity(cartRepoBySkuEntity.get().getQuantity()+quantity);
                cartRepo.save(cartRepoBySkuEntity.get());
                return "PRODUCT NAME : "+skuEntity.get().getProductEntity().getProductName()+"\n SIZE : "+skuEntity.get().getSkuSize()+"\n NO.OF : "+quantity+"\n IS ADDED TO CART SUCCESSFULLY";
            }
            else if(inventoryEntity.get().getQuantity()>=quantity && inventoryEntity.isPresent() && !cartRepoBySkuEntity.isPresent()) {
               CartEntity cartEntity = new CartEntity(quantity,skuEntity.get(),userEntity.get());
               cartRepo.save(cartEntity);
               return "PRODUCT NAME : "+skuEntity.get().getProductEntity().getProductName()+"\n SIZE : "+skuEntity.get().getSkuSize()+"\n NO.OF : "+quantity+"\n IS ADDED TO CART SUCCESSFULLY";
           }

           return "OUT OF STACK \n AVAILABLE STOCK : "+inventoryEntity.get().getQuantity();
    }
        return "SKU CODE IS NOT EXISTS";
    }
    public List<CartModel> getCartModel(){
        Optional<UserEntity> userEntity = userRepo.findById(myUserDetailsService.id);
        List<CartEntity> cartEntities = cartRepo.findByUserEntity(userEntity.get());
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
