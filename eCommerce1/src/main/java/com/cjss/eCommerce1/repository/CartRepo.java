package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.CartEntity;
import com.cjss.eCommerce1.entity.SKUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<CartEntity, Integer> {
    List<CartEntity> findByQuantityAndSkuEntity(Integer quantity, SKUEntity skuEntity);
    Optional <CartEntity> findBySkuEntity(SKUEntity skuEntity);
}
