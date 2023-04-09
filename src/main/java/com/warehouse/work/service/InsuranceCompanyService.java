package com.warehouse.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.work.entity.InsuranceCompany;
import com.warehouse.work.repository.InsuranceCompanyRepository;

@Service
public class InsuranceCompanyService extends CrudImpl<InsuranceCompany> {

	public InsuranceCompanyRepository repository;

	@Autowired
	public InsuranceCompanyService(InsuranceCompanyRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
