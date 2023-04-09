package com.warehouse.work.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Product extends AbstractEntity {

	private String name;
	@Column(length = 1000)
	private String imageUrl;

	@Column(length = 3000)
	private String description;

	private String sizes;

	private float weight;

	private float price;
	
	private int count;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Override
	public String toString() {
		return name + " (" + price+")";
	}
}