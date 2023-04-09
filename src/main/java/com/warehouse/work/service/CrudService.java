package com.warehouse.work.service;

public interface CrudService<Entity> {

	Entity create(Entity entity) throws Exception;

	Entity read(long id);

	boolean update(Entity entity) throws Exception;

	boolean delete(long id) throws Exception;
}