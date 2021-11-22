package com.qa.hr.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.qa.hr.entities.Email;
import com.qa.hr.entities.Employee;
import com.qa.hr.entities.EmployeeEmailDTO;
import com.qa.hr.entities.exceptions.EmailNotFound;
import com.qa.hr.entities.repos.EmailRepo;

@Service
public class EmailService {
	
	private EmailRepo repo;
	
	private EmployeeService service;
	
	private ApplicationContext context;
	
	@Autowired
	public EmailService(EmailRepo repo, EmployeeService service, ApplicationContext context) {
		super();
		this.repo = repo;
		this.service = service;
		this.context = context;
	}

	public boolean create(Email email) {
		repo.save(email);
		return true;
	}
	
	public Email findById(long id) {
		return repo.findById(id).orElseThrow(EmailNotFound::new);
	}
	
	public boolean deleteById(long id) {
		repo.deleteById(id);
		return !repo.existsById(id);
	}
	
	public boolean update(long id, Email changed) {
		if(deleteById(id)) {
			changed.setId(id);
			create(changed);
			return repo.existsById(id);
		}
		return false;
	}
	
	public List<EmployeeEmailDTO> emailDepartment(String jobTitle, Email email) {
		List<EmployeeEmailDTO> generated = new ArrayList<>();
		for(Employee employee: service.findByJobTitle(jobTitle)) {
			generated.add(EmployeeEmailDTO.mapToDTO(employee, email));
		}
		return generated;
	}
	
	public List<EmployeeEmailDTO> birthdayEmail() {
		List<EmployeeEmailDTO> generated = new ArrayList<>();
		for(Employee employee: service.birthday()) {
			generated.add(EmployeeEmailDTO.mapToDTO(employee, context.getBean("birthdayEmail", Email.class)));
		}
		return generated;
	}
}
