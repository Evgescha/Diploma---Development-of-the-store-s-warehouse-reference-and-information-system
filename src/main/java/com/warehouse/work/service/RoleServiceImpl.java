package com.warehouse.work.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.work.entity.Role;
import com.warehouse.work.repository.RoleRepository;

@Service
public class RoleServiceImpl extends CrudImpl<Role> {

	public RoleRepository repository;

	@Autowired
	public RoleServiceImpl(RoleRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public Role findByName(String name) {
		return repository.findByNameIgnoreCase(name);
	}

}
