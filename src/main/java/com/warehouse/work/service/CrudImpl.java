package com.warehouse.work.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public class CrudImpl<Entity> implements CrudService<Entity> {

	public JpaRepository<Entity, Long> repository;

	public Entity create(Entity entity) {
		try {
			return repository.saveAndFlush(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Entity read(long id) {
		return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
	}

	public boolean update(Entity entity) {
		try {
			repository.saveAndFlush(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(long id) throws Exception {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public CrudImpl(JpaRepository<Entity, Long> repository) {
		super();
		this.repository = repository;
	}

	public List<Entity> getAll() {
		List<Entity> result = repository.findAll();

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Entity>();
		}
	}
}
