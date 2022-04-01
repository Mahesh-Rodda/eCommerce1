package com.cjss.eCommerce1.repository;

import com.cjss.eCommerce1.entity.ReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRepo  extends JpaRepository<ReturnEntity,String> {
}
