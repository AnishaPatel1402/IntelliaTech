package com.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
