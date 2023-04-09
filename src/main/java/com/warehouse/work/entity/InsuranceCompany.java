package com.warehouse.work.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class InsuranceCompany extends AbstractEntity {
	
	private String name;
	
	private String adres;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "insuranceCompany")
	private List<Transporter> orders = new ArrayList<Transporter>();

	@Override
	public String toString() {
		return name;
	}
}
