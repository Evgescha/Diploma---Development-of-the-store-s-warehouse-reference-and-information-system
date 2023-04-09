package com.warehouse.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.work.entity.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
	OrderStatus findByName(String name);
	OrderStatus findByNameIgnoreCase(String name);
}
