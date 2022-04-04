package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.ShippingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepo extends JpaRepository<ShippingEntity,Integer> {
}
