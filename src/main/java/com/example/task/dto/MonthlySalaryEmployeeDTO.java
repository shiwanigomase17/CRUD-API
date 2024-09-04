package com.example.task.dto;



import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySalaryEmployeeDTO {
	
	//monthlySalary table fields
	  private Long salaryId;
	    private String salaryName;
	    private String month;
	    private Double salaryAmount;
	    private Long lossofpay;
	    
	    //employee table fields
	    private Long employeeId;
	    private String employeeName;
	    private String employeeEmpCode;
	    private String employeeAddress;
	    private LocalDate employeeJDate;
	   
	    private Double employeeInsurance;
	    
	    //todaycode
	 // Designation field
	    private String designationName;
	    
	    //salary table fields
	 // Salary fields
	    private Double basicSalary;
	    private Double grossSalary;
	    private Double ta;
	    private Double da;
	    private Double pf;
	    private Double other;
	    private Double deduction;
	    private Double finalBasicSalary;
	    
}
