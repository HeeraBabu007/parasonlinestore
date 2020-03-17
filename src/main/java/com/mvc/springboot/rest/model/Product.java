package com.mvc.springboot.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "Product_Id", nullable = false)
	private long proid;
	
	@Column(name = "Product_Name", length = 30, nullable = false)
	private String productName;

	@Column(name = "Product_Description", length = 30, nullable = false)
	private String productDescription;

	public long getProid() {
		return proid;
	}

	public void setProid(long proid) {
		this.proid = proid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	
}
