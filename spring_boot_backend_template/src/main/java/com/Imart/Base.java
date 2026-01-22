package com.Imart;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Base {
	
	@CreationTimestamp
  private Date Created_At;
	@UpdateTimestamp
	private Date Updated_At;
}
