package com.Imart.Entity;

	import jakarta.persistence.*;

	@Entity
	@Table(name = "cart_items")
	public class CartItem {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long cartItemId;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "cart_id")
	    private Cart cart;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;

	    private int quantity;
	    private double price;

	    // getters & setters
	}
