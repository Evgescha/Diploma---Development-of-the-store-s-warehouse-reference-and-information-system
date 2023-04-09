package com.warehouse.work.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name = "myUsers")
@Data
public class User extends AbstractEntity {

	@Column(unique = true)
	@NotNull
	private String fio;

	@Column(unique = true)
	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private Date dateBorn;

	@NotNull
	private String email;
	
	@NotNull
	private String phone;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
	private List<Order> myOrders = new ArrayList<Order>();

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public User() {
		super();
	}

	public List<String> getRoleListNames() {
		List<String> roleNames = new ArrayList<String>();
		roleNames.add(role.getName());
		return roleNames;
	}
	

	@Override
	public String toString() {
		return "User [fio=" + fio + ", username=" + username + ", password=" + password + ", dateBorn=" + dateBorn
				+ ", email=" + email + ", phone=" + phone + ", role=" + role + "]";
	}

	public void update(String fio, String username, String password, Date dateBorn, String email, String phone) {
		this.fio = fio;
		this.username = username;
		this.password = password;
		this.dateBorn = dateBorn;
		this.email = email;
		this.phone = phone;
	}
	

}
