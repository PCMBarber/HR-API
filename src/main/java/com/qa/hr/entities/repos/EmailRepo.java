package com.qa.hr.entities.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hr.entities.Email;

@Repository
public interface EmailRepo extends JpaRepository<Email, Long> {
	
}
