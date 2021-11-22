package com.qa.hr.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.qa.hr.entities.Employee;
import com.qa.hr.entities.exceptions.EmployeeNotFound;
import com.qa.hr.entities.repos.EmployeeRepo;

@Service
public class EmployeeService {
	
	private EmployeeRepo repo;
	
	private ApplicationContext context;
	
	@Autowired
	public EmployeeService(EmployeeRepo repo, ApplicationContext context) {
		super();
		this.repo = repo;
		this.context = context;
	}
	
	public Employee create(Employee employee) {
		repo.save(employee);
		return employee;
	}
	
	public List<Employee> readAll() {
		return repo.findAll();
	}
	
	public Employee findById(long id) {
		return repo.findById(id).orElseThrow(EmployeeNotFound::new);
	}
	
	public boolean deleteById(long id) {
		repo.deleteById(id);
		return !repo.existsById(id);
	}
	
	public boolean update(long id, Employee changed) {
		if(deleteById(id)) {
			changed.setId(id);
			create(changed);
			return repo.existsById(id);
		}
		return false;
	}
	
	public List<String> emailList() {
		List<String> emails = new ArrayList<>();
		for(Employee emp: readAll()) {
			emails.add(emp.getEmail());
		}
		return emails;
	}
	
	public List<Employee> findByName(String name) {
		return repo.findEmployeeByFirstName(name);
	}
	
	public List<Employee> findByJobTitle(String jobTitle) {
		return repo.findEmployeeByJobTitle(jobTitle);
	}
	
	public List<Employee> age(long age, boolean older) {
		List<Employee> found = new ArrayList<>();
		for(Employee emp: readAll()) {
			LocalDate now = context.getBean("timeNow", LocalDate.class);
			Period calc = Period.between(emp.getDOB().toLocalDate(), now);
			long employeeAge = calc.getYears();
			if(employeeAge >= age && older) {
				found.add(emp);
			} else if(employeeAge <= age && !older) {
				found.add(emp);
			}
		}
		return found;
	}
	
	public List<Employee> birthday() {
		List<Employee> found = new ArrayList<>();
		for(Employee emp: readAll()) {
			LocalDate now = context.getBean("timeNow", LocalDate.class);
			Period calc = Period.between(emp.getDOB().toLocalDate(), now);
			long daysToBirthday = calc.getDays();
			if(daysToBirthday == 0) {
				found.add(emp);
			}
		}
		return found;
	}
}
