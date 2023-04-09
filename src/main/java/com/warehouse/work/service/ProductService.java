package com.warehouse.work.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.work.entity.Product;
import com.warehouse.work.repository.ProductRepository;

@Service
public class ProductService extends CrudImpl<Product> {

	public ProductRepository repository;

	@Autowired
	public ProductService(ProductRepository repository) {
		super(repository);
		this.repository = repository;
	}


}
