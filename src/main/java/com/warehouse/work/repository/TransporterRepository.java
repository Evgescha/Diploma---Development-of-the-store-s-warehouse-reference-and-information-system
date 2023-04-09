package com.warehouse.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.work.entity.Transporter;

@Repository
public interface TransporterRepository extends JpaRepository<Transporter, Long> {
	Transporter findByName(String name);

	Transporter findByNameIgnoreCase(String name);
}
