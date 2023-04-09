package com.warehouse.work.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.work.entity.Order;
import com.warehouse.work.entity.OrderStatus;
import com.warehouse.work.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByCreator(User creator);

	List<Order> findByStatus(OrderStatus status);


	Order findByCreatorAndStatus(User creator, OrderStatus status);	
	Order findByCreatorAndDateOrder(User creator,Date dates);	
	Order findByCreatorAndStatusAndDateOrder(User creator,OrderStatus status,Date dates);
}
