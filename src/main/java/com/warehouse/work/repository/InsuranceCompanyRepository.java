package com.warehouse.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.work.entity.InsuranceCompany;

@Repository
public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Long> {
	InsuranceCompany findByName(String name);

	InsuranceCompany findByNameIgnoreCase(String name);
}
