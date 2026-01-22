package com.Imart;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends Base {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
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
    
}
