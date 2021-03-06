package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.OrderEntity;
import com.cjss.eCommerce1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,Integer> {
    List<OrderEntity> findByUserEntity(UserEntity userEntity);
}
