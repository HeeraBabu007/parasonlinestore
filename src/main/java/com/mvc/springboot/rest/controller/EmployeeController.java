package com.mvc.springboot.rest.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mvc.springboot.rest.model.Employee;
import com.mvc.springboot.rest.model.EmployeeNotFoundException;
import com.mvc.springboot.rest.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	public EmployeeRepository employeeRepo;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepo.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Employee GetEmployeeById(@PathVariable long id) {
		Optional<Employee> Employee=employeeRepo.findById(id);
		if(!Employee.isPresent())
			throw new EmployeeNotFoundException("id-"+id);
		return Employee.get();		
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeRepo.deleteById(id);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee emp){
		Employee saveEmployeeObj=employeeRepo.save(emp);
		URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveEmployeeObj.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee emp, @PathVariable long id){
		Optional<Employee> Employee=employeeRepo.findById(id);
		if(!Employee.isPresent())
			return ResponseEntity.notFound().build();
		emp.setId(id);
		employeeRepo.save(emp);
		return ResponseEntity.noContent().build();		
	}
	
}
