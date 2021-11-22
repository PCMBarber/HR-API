package com.qa.hr.entities;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEmailDTO {

	private String firstName;

	private String lastName;

	private String email;

	private String subject;

	private String content;
	
	public static EmployeeEmailDTO mapToDTO(Employee emp, Email mail) {
		return new EmployeeEmailDTO(emp.getFirstName(),emp.getLastName());
	}
}
