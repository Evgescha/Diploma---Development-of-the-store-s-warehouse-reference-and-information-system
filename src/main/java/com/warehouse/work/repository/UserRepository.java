package com.warehouse.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.work.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsernameIgnoreCase(String username);

	public User findByUsername(String username);
}
