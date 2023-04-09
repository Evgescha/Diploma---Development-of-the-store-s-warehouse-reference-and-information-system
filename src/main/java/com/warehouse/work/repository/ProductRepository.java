package com.warehouse.work.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.work.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findByNameIgnoreCase(String name);
	public List<Product> findByNameContains(String name);

	public List<Product> findByPriceBetween(float startPrice, float endPrice);
	
	public List<Product> findByNameContainsAndPriceBetween(String name, float startPrice, float endPrice);
}
