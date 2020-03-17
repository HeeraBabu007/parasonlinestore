package com.mvc.springboot.rest.model;

public class ProductNotFoundException  extends RuntimeException{

	public ProductNotFoundException(String exception) {
		super(exception);
	}
}
