package com.espublico.importadorPedidos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "rol_id"))
	private List<Roles> roles = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PurchaseOrder> purchaseOrders;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<HistoryOrder> orderHistories;
	
	public User () {
		// Constructor vacío necesario para JPA
	}
	
	

	public User(Long userId, String userName, String password, String email, List<Roles> roles,
			Set<PurchaseOrder> purchaseOrders, Set<HistoryOrder> orderHistories) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.purchaseOrders = purchaseOrders;
		this.orderHistories = orderHistories;
	}

	public User( String userName, String password, String email, List<Roles> roles
			) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	public Set<HistoryOrder> getOrderHistories() {
		return orderHistories;
	}

	public void setOrderHistories(Set<HistoryOrder> orderHistories) {
		this.orderHistories = orderHistories;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
}
