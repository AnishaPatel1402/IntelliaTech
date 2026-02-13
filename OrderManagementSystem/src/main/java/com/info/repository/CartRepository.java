package com.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
