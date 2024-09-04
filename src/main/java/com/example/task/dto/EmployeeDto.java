package com.example.task.dto;

import java.time.LocalDate;

public class EmployeeDto {

    private String name;
//    private String empCode;
    private String address;
    private LocalDate jDate;
    private Long designationId;
    private Double grossSalary;
    private String designation;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String getEmpCode() {
//		return empCode;
//	}
//	public void setEmpCode(String empCode) {
//		this.empCode = empCode;
//	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getjDate() {
		return jDate;
	}
	public void setjDate(LocalDate jDate) {
		this.jDate = jDate;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Double getGrossSalary() {
		return grossSalary;
	}
	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	

   
    
}


