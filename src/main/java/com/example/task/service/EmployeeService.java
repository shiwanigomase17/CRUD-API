package com.example.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.task.dto.EmployeeDto;
import com.example.task.model.Designation;
import com.example.task.model.Employee;
import com.example.task.model.Salary;
import com.example.task.repository.DesignationRepository;
import com.example.task.repository.EmployeeRepository;
import com.example.task.repository.SalaryRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Transactional
    public Employee createEmployee(EmployeeDto employeeDto) {
    	
    	Designation designation = null;
        
       
        if (employeeDto.getDesignationId() != null) { 
            designation = designationRepository.findById(employeeDto.getDesignationId())
                    .orElseThrow(() -> new RuntimeException("Designation not found with ID: " + employeeDto.getDesignationId()));
        } else {
           
            designation = designationRepository.findByDesignation(employeeDto.getDesignation())
                    .orElseGet(() -> {
                        Designation newDesignation = new Designation();
                        newDesignation.setDesignation(employeeDto.getDesignation());
                        return designationRepository.save(newDesignation);
                    });
        }
       
//    	Designation designation = designationRepository.findById(employeeDto.getDesignationId())
//                .orElseGet(() -> {
//                    Designation newDesignation = new Designation();
//                    newDesignation.setDesignation(employeeDto.getDesignation());
//                    return designationRepository.save(newDesignation);
//                });
//        
        Salary salary = new Salary();
        salary.setGrossSalary(employeeDto.getGrossSalary());
       
        
//        salary.setBasicSalary(calculateBasicSalary(employeeDto.getGrossSalary()));
        
         Double basicSalary = calculateBasicSalary(employeeDto.getGrossSalary());
         salary.setBasicSalary(basicSalary);

       
        Double pf = calculatePF(basicSalary);
        salary.setPf(pf);
        //salary.setFinalBasicSalary(calculateFinalBasicSalary(basicSalary,pf));
     
        salary.setTa(calculateTA(employeeDto.getGrossSalary()));
        salary.setDa(calculateDA(employeeDto.getGrossSalary()));
       // salary.setPf(calculatePF(employeeDto.getGrossSalary()));
        salary.setOther(calculateOther(employeeDto.getGrossSalary()));
        
       

//        salary = salaryRepository.save(salary);

       
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
//       
        employee.setAddress(employeeDto.getAddress());
        employee.setjDate(employeeDto.getjDate());
        employee.setDesignation(designation);
        employee.setSalary(salary);
        
        
        employee.setSalary(salary);
        salary.setEmployee(employee);
      
        
     
        Double annualInsurance = (double) 1000 ;
        Double monthlyInsurance = annualInsurance / 12; 
        employee.setInsurance(monthlyInsurance);
        
        Double totalDeduction = calculateDeduction(pf, monthlyInsurance);
        salary.setDeduction(totalDeduction);
        
        Double finalBasicSalary = calculateFinalBasicSalary(employeeDto.getGrossSalary(), totalDeduction);
        salary.setFinalBasicSalary(finalBasicSalary);
        
        
        
        
        Employee empcode2 = employeeRepository.findLastEmployee();

        if (empcode2 != null) {
            String empCode = empcode2.getEmpCode();
            int value = Integer.parseInt(empCode.substring(2));
            value++;
            employee.setEmpCode("CT" + value);
        } else {
            employee.setEmpCode("CT1001");
        }
        return employeeRepository.save(employee);
        
        
    }

   
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Employee not found"));
        		
    }

    private Double calculateBasicSalary(Double grossSalary) {
       
        return grossSalary * 0.4; 
    }

    private Double calculateFinalBasicSalary(Double grossSalary, Double totalDeduction) {
       
        return grossSalary -  totalDeduction; 
    }

    private Double calculateTA(Double grossSalary) {
        
        return grossSalary * 0.2; 
    }

    private Double calculateDA(Double grossSalary) {
        
        return grossSalary * 0.2; 
    }

    private Double calculatePF(Double basicSalary) {
       
        return basicSalary * 0.125; 
//    	Double p = basicSalary * 0.125;
//    	return p;
    }

    private Double calculateOther(Double grossSalary) {
       
        return grossSalary * 0.2; 
    }

    private Double calculateDeduction(Double pf, Double monthlyInsurance) {
    	
    	return pf + monthlyInsurance;
    }
    
   

    @Transactional
    public Employee updateEmployeeById(Long id, EmployeeDto employeeDto) {
    	
    	Optional<Employee> e = employeeRepository.findById(id);
    	
    	
//    	Salary salary = new Salary();
//    	Employee employee = new Employee();
        e.get().getSalary().setGrossSalary(employeeDto.getGrossSalary());
        
        Double basicSalary = calculateBasicSalary(employeeDto.getGrossSalary());
        e.get().getSalary().setBasicSalary(basicSalary);

       
        Double pf = calculatePF(basicSalary);
        e.get().getSalary().setPf(pf);
        //salary.setPf(calculatePF(12.5));
        e.get().getSalary().setFinalBasicSalary(calculateFinalBasicSalary(basicSalary,pf));
     
        e.get().getSalary().setTa(calculateTA(employeeDto.getGrossSalary()));
        e.get().getSalary().setDa(calculateDA(employeeDto.getGrossSalary()));
        //salary.setPf(calculatePF(employeeDto.getGrossSalary()));
        e.get().getSalary().setOther(calculateOther(employeeDto.getGrossSalary()));
        
        Double annualInsurance = (double) 1000 ;
        Double monthlyInsurance = annualInsurance / 12; 
        e.get().setInsurance(monthlyInsurance);
        Double totalDeduction = calculateDeduction(pf, monthlyInsurance);
        e.get().getSalary().setDeduction(totalDeduction);
        Double finalBasicSalary = calculateFinalBasicSalary(employeeDto.getGrossSalary(), totalDeduction);
        e.get().getSalary().setFinalBasicSalary(finalBasicSalary);
        
        e.get().setName(employeeDto.getName());
        
        e.get().setAddress(employeeDto.getAddress());
        
        e.get().setjDate(employeeDto.getjDate());
        
	
       return employeeRepository.save(e.get());
	
	}


	@Transactional
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteEmployeeById(id);

    }


	public List<Employee> getEmployeesByDesignation(String designation) {
        return employeeRepository.findEmployeesByDesignation(designation);
    }


	public List<Employee> findAll() {
	
		return employeeRepository.findAll();
	}
	
	

	

//	public List<Employee> getEmployeesByDesignation(String name, String salaryMonth) {
//		
//		return employeeRepository.findByNameAndMonth(name, salaryMonth);
//	}



 
}



