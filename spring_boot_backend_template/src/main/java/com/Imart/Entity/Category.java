package com.Imart.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
  @Id
  private long category_id;
  private String name;
  private String description;
}
