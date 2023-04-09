package com.warehouse.work.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Transporter extends AbstractEntity {
	
	private String name;
	
	private String adres;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transporter")
	private List<Order> orders = new ArrayList<Order>();

	@ManyToOne
	@JoinColumn(name = "transporter_id")
	private InsuranceCompany insuranceCompany;
	
	@Override
	public String toString() {
		return name+", "+adres;
	}
}
