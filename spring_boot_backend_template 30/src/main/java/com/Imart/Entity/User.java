package com.Imart.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@Table(name = "users")

public class User extends Base {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
	@Column(length=30)
	private String name;
	@Column(length=50)
    private String email;
	@Column (length=200)
	private String password;
	@Column (length=15)
	private String phone;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column(length=50)
	private String address;
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + ", role=" + role + ", address=" + address + "]";
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
}
