package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity,String> {
}
