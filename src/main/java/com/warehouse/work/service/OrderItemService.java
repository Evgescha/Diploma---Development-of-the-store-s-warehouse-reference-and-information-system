package com.warehouse.work.service;


import com.warehouse.work.entity.OrderItem;
import com.warehouse.work.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends CrudImpl<OrderItem> {

    public OrderItemRepository repository;

    @Autowired
    public OrderItemService(OrderItemRepository repository) {
        super(repository);
        this.repository = repository;
    }
}