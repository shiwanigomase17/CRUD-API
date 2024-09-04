package com.example.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task.dto.MonthlySalaryEmployeeDTO;
import com.example.task.model.MonthlySalary;
import com.example.task.repository.MonthlySalaryRepository;

@Service
public class MonthlySalaryService {
	
	@Autowired
	private MonthlySalaryRepository monthlySalaryRepository;

	public MonthlySalary createEmployee(MonthlySalary monthlySalary) {
		
		MonthlySalary	monthlySalary2 = monthlySalaryRepository.save(monthlySalary);
		
		return monthlySalary2;
		
	}

	public Optional<MonthlySalary> getByNameAndMonth(String name, String month) {
        return monthlySalaryRepository.findByNameAndMonth(name, month);
    }
	
	public String validate(String name, String month) {
	    Optional<MonthlySalary> monthlySalary = monthlySalaryRepository.findByNameAndMonth(name, month);
	    if (monthlySalary.isPresent()) {
	        return "false";
	    } else {
	        return "true";
	    }
	}

	public int deleteByNameAndMonth(String name, String month) {
		
		return monthlySalaryRepository.deleteByNameAndMonth(name, month);
	}
	
	public List<MonthlySalaryEmployeeDTO> getByNameAndMonth21(String name, String month ) {
	    return monthlySalaryRepository.findByNameAndMonth21(name, month);
	}


}
