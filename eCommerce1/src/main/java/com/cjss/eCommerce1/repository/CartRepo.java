package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.CartEntity;
import com.cjss.eCommerce1.entity.SKUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartEntity,String> {
    List<CartEntity> findByQuantityAndSkuEntity(Integer quantity, SKUEntity skuEntity);
}
