package com.example.task.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.task.dto.EmployeeDto;
import com.example.task.dto.MonthlySalaryEmployeeDTO;
import com.example.task.model.Employee;
import com.example.task.model.MonthlySalary;
import com.example.task.service.MonthlySalaryService;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin("*")
public class MonthlySalaryController {
	
	@Autowired
	private MonthlySalaryService monthlySalaryService;
	
	@PostMapping("/salPost")
    public MonthlySalary createEmployee(@RequestBody MonthlySalary monthlySalary) {
		
	//		System.out.println("ssssssssssssssssssssssssssssssssss"+ monthlySalary);
		
    	
    	monthlySalaryService.createEmployee(monthlySalary);
         
         return monthlySalary;
    }
	

	 @GetMapping("/findByNameAndMonth")
	    public ResponseEntity<MonthlySalary> getByNameAndMonth(
	            @RequestParam String name,
	            @RequestParam String month
//	            @RequestParam Long lossofpay
	            ) {
		 
		  Optional<MonthlySalary> monthlySalary = monthlySalaryService.getByNameAndMonth(name, month);
	        
	        System.out.println("111111111111111111111111111111111111111"+monthlySalary);
//	        
	        
	        return monthlySalary.map(ResponseEntity::ok)
	                            .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	 
	 
	 
	 //new API
	 @GetMapping("/findByNameAndMonth2")
	 public ResponseEntity<List<MonthlySalaryEmployeeDTO>> getByNameAndMonth21(
	         @RequestParam String name,
	         @RequestParam String month
//	         @RequestParam Long lossofpay
	         ) {

	     List<MonthlySalaryEmployeeDTO> monthlySalaries = monthlySalaryService.getByNameAndMonth21(name, month);

	     if (monthlySalaries.isEmpty()) {
	         return ResponseEntity.notFound().build();
	     }

	     return ResponseEntity.ok(monthlySalaries);
	 }

	 
	 
	 
	 @GetMapping("/validate")
	    public String validate(
	            @RequestParam String name,
	            @RequestParam String month
//	            @RequestParam Long lossofpay
	            ) {
	        return monthlySalaryService.validate(name, month);
	    }
	 
	 @Transactional
	 @DeleteMapping("/deleteByNameAndMonth")
	 public String deleteByNameAndMonth(@RequestParam String name, @RequestParam String month) {
	     int rowsDeleted = monthlySalaryService.deleteByNameAndMonth(name, month);
	     
	     if (rowsDeleted > 0) {
	         return "Deleted successfully";
	     } else {
	         return "No record found to delete";
	     }
	 }

	 }


