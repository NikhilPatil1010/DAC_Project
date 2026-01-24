package com.Imart.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	@Id
   private long product_id;
	@Column(length=30)
  private String name;
  private String description;
  private Double price;
  private long stock;
  @ManyToOne
  @JoinColumn(name="fid")
  private Category category_id;
  private String image_url;
}
