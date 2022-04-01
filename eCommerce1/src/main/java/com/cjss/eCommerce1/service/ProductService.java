package com.cjss.eCommerce1.service;

import com.cjss.eCommerce1.entity.InventoryEntity;
import com.cjss.eCommerce1.entity.PriceEntity;
import com.cjss.eCommerce1.entity.ProductEntity;
import com.cjss.eCommerce1.entity.SKUEntity;
import com.cjss.eCommerce1.model.ProductModel;
import com.cjss.eCommerce1.model.SKUModel;
import com.cjss.eCommerce1.repository.InventoryRepo;
import com.cjss.eCommerce1.repository.PriceRepo;
import com.cjss.eCommerce1.repository.ProductRepo;
import com.cjss.eCommerce1.repository.SKURepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SKURepo skuRepo;
    @Autowired
    private PriceRepo priceRepo;
    @Autowired
    private InventoryRepo inventoryRepo;

    public String addProduct(ProductModel productModel){
//        ProductEntity productEntity = new ProductEntity(productModel.getProductCode(),productModel.getProductName(),productModel.getDescription());
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productModel,productEntity);
        ProductEntity productEntity1 = productRepo.save(productEntity);
         return  productEntity1.getProductCode()+" IS ADDED SUCCESSFULLY";
    }
    public String addProductSKU(SKUModel skuModel){
        Optional<ProductEntity> productEntity =productRepo.findById(skuModel.getProductCode());
        if (productEntity.isPresent()) {
            SKUEntity skuEntity = new SKUEntity(skuModel.getSkuCode(),skuModel.getSkuSize(),productEntity.get());
            SKUEntity skuEntity1 = skuRepo.save(skuEntity);
            PriceEntity priceEntity = new PriceEntity(skuEntity1.getSkuCode(),skuModel.getSkuPrice());
            priceRepo.save(priceEntity);
            InventoryEntity inventoryEntity = new InventoryEntity(skuEntity1.getSkuCode(),skuModel.getAvailableQuantity());
            inventoryRepo.save(inventoryEntity);
            return skuEntity1.getSkuCode()+" IS ADDED SUCCESSFULLY";
        }
       return  skuModel.getProductCode()+" IS NOT EXISTS";
    }
    public String updateProduct(ProductModel productModel){
       Optional<ProductEntity> productEntity = productRepo.findById(productModel.getProductCode());
       String result = null;
       if(productEntity.isPresent()){
           if(productModel.getProductName() != null){productEntity.get().setProductName(productModel.getProductName());
               result = productModel.getProductCode()+" UPDATED : "+productModel.getProductName();
           }
          if(productModel.getDescription() != null){productEntity.get().setDescription(productModel.getDescription());
              result = productModel.getProductCode()+" UPDATED : "+productModel.getDescription();
          }
       }
       result = productModel.getProductCode()+"NOT EXISTS";
       return result;
    }
    public String updateSKU(SKUModel skuModel){
       Optional<SKUEntity> skuEntity = skuRepo.findById(skuModel.getSkuCode());
       Optional<InventoryEntity> inventoryEntity = inventoryRepo.findById(skuModel.getSkuCode());
        Optional<PriceEntity> priceEntity = priceRepo.findById(skuModel.getSkuCode());
        String report = null;
       if (skuEntity.isPresent() && inventoryEntity.isPresent() && priceEntity.isPresent()){
           if (skuModel.getSkuSize() != null){skuEntity.get().setSkuSize(skuModel.getSkuSize());
               report = skuModel.getSkuCode()+" UPDATED : "+skuModel.getSkuSize();
           }
           if (skuModel.getProductCode() != null){
               skuEntity.get().setProductEntity(productRepo.findById(skuModel.getProductCode()).get());
               report = skuModel.getSkuCode()+" UPDATED : "+skuModel.getProductCode();
           }
           if (skuModel.getAvailableQuantity() != 0){inventoryEntity.get().setQuantity(skuModel.getAvailableQuantity());
               report = skuModel.getSkuCode()+" TOTAL QUANTITY : "+skuModel.getAvailableQuantity();
           }
           if (skuModel.getSkuPrice() != 0){priceEntity.get().setPrice(skuModel.getSkuPrice());
               report = skuModel.getSkuCode()+" PRICE : "+skuModel.getSkuPrice();
           }
       }
    report = skuModel.getSkuCode()+" NOT EXISTS";
       return report;
    }
//    public String deleteSku
}

