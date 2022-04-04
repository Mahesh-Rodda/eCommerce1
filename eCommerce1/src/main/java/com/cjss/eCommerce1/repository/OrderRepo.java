package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,Integer> {
}
