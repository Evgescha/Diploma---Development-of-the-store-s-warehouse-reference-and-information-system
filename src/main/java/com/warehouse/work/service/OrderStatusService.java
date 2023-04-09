package com.warehouse.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.work.entity.OrderStatus;
import com.warehouse.work.repository.OrderStatusRepository;

@Service
public class OrderStatusService extends CrudImpl<OrderStatus> {

	public OrderStatusRepository repository;

	@Autowired
	public OrderStatusService(OrderStatusRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
