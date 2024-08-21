package com.example.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.task.dto.EmployeeDto;
import com.example.task.model.Designation;
import com.example.task.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	 @Query(value = "SELECT * FROM Employee ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Employee findLastEmployee();

//	List<Employee> findByDesignation(Designation designation);
	 
	 @Query("SELECT e FROM Employee e WHERE e.designation.designation = :designation")
	    List<Employee> findEmployeesByDesignation(@Param("designation") String designation);

	Employee save(EmployeeDto employeeDto);
	

	void deleteEmployeeById(Long id);

//	List<Employee> findByDesignation(String designation);
}
