package com.mvc.springboot.rest.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mvc.springboot.rest.model.Product;
import com.mvc.springboot.rest.repository.ProductRepository;

@RestController
@Transactional
public class ProductController {

	@Autowired
	public ProductRepository productRepo;
	
	@GetMapping("/fetch/products")
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	@PostMapping("/save/product")
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		Product saveProduct=productRepo.save(product);
		URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveProduct.getProid()).toUri();
		return ResponseEntity.created(location).build();
	}
}
