package com.example.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.model.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {
	 

	//Optional<Designation> findByDesignation(Designation designation);

//	Optional<Designation> findByDesignation(String designation);
//	Optional<Designation> findById(Long id);
//	Optional<Designation> findByDesignation(Designation designation);
	Designation getByDesignation(String designation);

	Optional<Designation> findByDesignation(String designation);

	

	
}

