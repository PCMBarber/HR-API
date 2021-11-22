package com.qa.hr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hr.entities.Employee;
import com.qa.hr.services.EmployeeService;

@RestController
public class EmployeeController {

	private EmployeeService service;

	@Autowired
	public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Employee> create(@RequestBody Employee employee) {
		return new ResponseEntity<>(service.create(employee), HttpStatus.CREATED);
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<Employee> update(@PathVariable long id, @RequestBody Employee employee) {
		if (service.update(id, employee)) {
			return new ResponseEntity<>(service.findById(id), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		if (service.deleteById(id)) {
			return "Employee Deleted";
		}
		return "Delete Failed";
	}

	@GetMapping("/readAll")
	public List<Employee> readAll() {
		return service.readAll();
	}

	@GetMapping("/readById/{id}")
	public Employee readById(@PathVariable long id) {
		return service.findById(id);
	}

	@GetMapping("/readByJobTitle/{jodTitle}")
	public List<Employee> readByJobTitle(@PathVariable String jodTitle) {
		return service.findByJobTitle(jodTitle);
	}

	@GetMapping("/emailList")
	public List<String> emailList() {
		return service.emailList();
	}

	@GetMapping("/ageAbove/{age}")
	public List<Employee> ageAbove(@PathVariable long age) {
		return service.age(age, true);
	}

	@GetMapping("/ageBelow/{age}")
	public List<Employee> ageBelow(@PathVariable long age) {
		return service.age(age, false);
	}

	@GetMapping("/birthdayList")
	public List<Employee> birthdayList() {
		return service.birthday();
	}
}
