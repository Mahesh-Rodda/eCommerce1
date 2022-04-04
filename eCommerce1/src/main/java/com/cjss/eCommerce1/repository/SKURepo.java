package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.SKUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SKURepo extends JpaRepository<SKUEntity,Integer> {
}
