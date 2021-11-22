package com.qa.hr.entities.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hr.entities.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	public List<Employee> findEmployeeByFirstName(String firstName);
	
	public List<Employee> findEmployeeByJobTitle(String jobTitle);
}
