package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.PackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingRepo extends JpaRepository<PackingEntity,Integer> {
}
