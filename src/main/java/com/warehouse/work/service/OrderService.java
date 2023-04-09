package com.warehouse.work.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.work.entity.Order;
import com.warehouse.work.repository.OrderRepository;

@Service
public class OrderService extends CrudImpl<Order> {

	public OrderRepository repository;

	@Autowired
	public OrderService(OrderRepository repository) {
		super(repository);
		this.repository = repository;
	}
}
