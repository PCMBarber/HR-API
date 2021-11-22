package com.qa.hr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hr.entities.Email;
import com.qa.hr.entities.EmployeeEmailDTO;
import com.qa.hr.services.EmailService;

@RestController
public class EmailController {
	
	private EmailService service;

	@Autowired
	public EmailController(EmailService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/createEmail")
	public String create(@RequestBody Email email) {
		if (service.create(email)) {
			return "Email Created";
		}
		return "Creation Failed";
	}

	@PostMapping("/updateEmail/{id}")
	public String update(@PathVariable long id, @RequestBody Email email) {
		if (service.update(id, email)) {
			return "Email Updated";
		}
		return "Update Failed";
	}

	@DeleteMapping("/deleteEmail/{id}")
	public String delete(@PathVariable long id) {
		if (service.deleteById(id)) {
			return "Email Deleted";
		}
		return "Delete Failed";
	}

	@GetMapping("/readEmailById/{id}")
	public Email readById(@PathVariable long id) {
		return service.findById(id);
	}
	
	@GetMapping("/emailDepartment/{jobTitle}")
	public List<EmployeeEmailDTO> emailDepartment(@PathVariable String jobTitle, @RequestBody Email email) {
		return service.emailDepartment(jobTitle, email);
	}
	
	@GetMapping("/birthday")
	public List<EmployeeEmailDTO> birthdayEmail() {
		return service.birthdayEmail();
	}
}
