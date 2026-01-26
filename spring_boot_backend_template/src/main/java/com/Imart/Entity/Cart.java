package com.Imart.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
	@Entity
	@Table(name = "carts")
	public class Cart {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long cartId;

	    @OneToOne
	    @JoinColumn(name = "user_id", nullable = false, unique = true)
	    private User user;

	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<CartItem> items = new ArrayList<>();

	    private double totalAmount;

	    // getters & setters
	}
