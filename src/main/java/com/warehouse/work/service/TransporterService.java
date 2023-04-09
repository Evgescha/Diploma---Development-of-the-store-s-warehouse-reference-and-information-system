package com.warehouse.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.work.entity.Transporter;
import com.warehouse.work.repository.TransporterRepository;

@Service
public class TransporterService extends CrudImpl<Transporter> {

	public TransporterRepository repository;

	@Autowired
	public TransporterService(TransporterRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
