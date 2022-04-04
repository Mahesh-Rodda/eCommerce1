package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryEntity,Integer> {
}
