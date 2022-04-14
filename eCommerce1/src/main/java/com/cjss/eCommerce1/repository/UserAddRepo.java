package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddRepo extends JpaRepository<UserAddressEntity,Integer> {
}
