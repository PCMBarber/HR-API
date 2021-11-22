package com.qa.hr.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.hr.entities.Employee;
import com.qa.hr.entities.repos.EmployeeRepo;

@SpringBootTest
class EmployeeServiceTest {
	
	@MockBean
	EmployeeRepo repo;
	
	@Autowired
    private EmployeeService service;
	
	@Test
	void testCreate() {
		Employee testEmp = new Employee();
		assertThat(service.create(testEmp)).isEqualTo(testEmp);
		verify(repo, times(1)).save(testEmp);
	}
	
	@Test
	void testReadAll() {
		when(repo.findAll()).thenReturn(new ArrayList<>());
		assertThat(service.readAll()).isEqualTo(new ArrayList<>());
		verify(repo, times(1)).findAll();
	}
	
	@Test
	void testFindById() {
		Employee testEmp = new Employee();
		Optional<Employee> option = Optional.of(testEmp);
		when(repo.findById((long) 1)).thenReturn(option);
		assertThat(service.findById((long) 1)).isEqualTo(testEmp);
		verify(repo, times(1)).findById((long) 1);
	}
	
	@Test
	void testDeleteById() {
		when(repo.existsById((long) 1)).thenReturn(false);
		assertThat(service.deleteById((long) 1)).isEqualTo(true);
		verify(repo, times(1)).deleteById((long) 1);
		verify(repo, times(1)).existsById((long) 1);
	}
	
	@Test
	void testUpdate() {
		Employee testEmp = new Employee();
		when(repo.existsById((long) 1)).thenReturn(false, true);
		
		assertThat(service.update((long) 1, testEmp)).isEqualTo(true);
		
		testEmp.setId((long) 1);
		verify(repo, times(1)).deleteById((long) 1);
		verify(repo, times(2)).existsById((long) 1);
		verify(repo, times(1)).save(testEmp);
	}
	
	@Test
	void testEmailList() {
		List<Employee> employees = new ArrayList<>();
		List<String> emails = new ArrayList<>();
		emails.add("PiersyB@no.co.uk");
		employees.add(new Employee(1,"Piers","Barber",Date.valueOf("1996-04-10"),"Consultant","PiersyB@no.co.uk"));
		when(repo.findAll()).thenReturn(employees);
		
		assertThat(service.emailList()).isEqualTo(emails);
		
		verify(repo, times(1)).findAll();
	}

}
