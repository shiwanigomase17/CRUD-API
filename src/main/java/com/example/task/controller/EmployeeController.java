package com.example.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.task.dto.EmployeeDto;
import com.example.task.model.Designation;
import com.example.task.model.Employee;
import com.example.task.repository.EmployeeRepository;
import com.example.task.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/empPost")
    public String createEmployee(@RequestBody EmployeeDto employeeDto) {
    	
    	employeeService.createEmployee(employeeDto);
         
         return "Employee added successfully";
    }

    @GetMapping("/empId/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }
    

    @GetMapping("/empDsg/{designation}")
    public List<Employee> getByDesignation(@PathVariable String designation)
    {
    	return employeeService.getEmployeesByDesignation(designation);
    }
    
    
    @GetMapping("/empAllEmp")
    public List<Employee> getAllEmp(){
    	return employeeService.findAll();
    }
    
    
  

	  @PutMapping("/updateEmp/{id}")
	    public String updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
	        
	          employeeService.updateEmployeeById( id, employeeDto);
	          return "Employee Details updated successfully";
	    }
	  
	  
	  
	  
	  @DeleteMapping("/deleteEmp/{id}")
	    public String deleteEmployeeById(@PathVariable("id") Long id) {
	       employeeService.deleteEmployeeById(id);
	       return "employee details deleted successfully";

	    }
	  
//	  @GetMapping("/findByNameAndMonth")
//	   public List<Employee> getEmployeeByNameAndMonth(@RequestParam String name, @RequestParam String salaryMonth){
//		  return employeeService.getEmployeesByDesignation(name,salaryMonth);
//	  }
}



