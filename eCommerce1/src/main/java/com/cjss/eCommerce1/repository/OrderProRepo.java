package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.OrderEntity;
import com.cjss.eCommerce1.entity.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProRepo extends JpaRepository<OrderProducts, Integer> {
   List<OrderProducts> findByOrderEntity(OrderEntity orderEntity);
}
