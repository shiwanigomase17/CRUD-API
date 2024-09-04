package com.example.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.task.dto.MonthlySalaryEmployeeDTO;
import com.example.task.model.MonthlySalary;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthlySalaryRepository extends JpaRepository<MonthlySalary, Long> {
   @Query("SELECT m FROM MonthlySalary  m WHERE  m.name = :name AND m.month = :month")
	
//	@Query("SELECT m FROM MonthlySalary m JOIN Employee e ON m.employee = e.id WHERE m.name = :name AND m.month = :month")
    Optional<MonthlySalary> findByNameAndMonth(@Param("name") String name, @Param("month") String month);

	@Modifying
	@Query("DELETE FROM MonthlySalary m WHERE m.name = :name AND m.month = :month")
	int deleteByNameAndMonth(@Param("name") String name, @Param("month") String month);

	
	
//	@Query("SELECT new com.example.task.dto.MonthlySalaryEmployeeDTO(" +
//		       "m.id, m.name, m.month, m.salary, " +
//		       "e.id, e.name, e.empCode, e.address, e.jDate, " +
//		       " e.insurance) " +
//		       "FROM MonthlySalary m " +
//		       "JOIN m.employee2 e " +
//		       "WHERE m.name = :name AND m.month = :month")
	
	
	@Query("SELECT new com.example.task.dto.MonthlySalaryEmployeeDTO(" +
		       "m.id, m.name, m.month, m.salary, m.lossofpay, " +
		       "e.id, e.name, e.empCode, e.address, e.jDate, e.insurance, " +
		       "d.designation, " +
		       "s.basicSalary, s.grossSalary, s.ta, s.da, s.pf, s.other, s.deduction, s.finalBasicSalary) " +
		       "FROM MonthlySalary m " +
		       "JOIN m.employee2 e " +
		       "JOIN e.designation d " +
		       "JOIN e.salary s " +
		       "WHERE m.name = :name AND m.month = :month")
		List<MonthlySalaryEmployeeDTO> findByNameAndMonth21(@Param("name") String name, @Param("month") String month);




}

