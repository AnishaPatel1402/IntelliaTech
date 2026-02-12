package com.info.dto;

import jakarta.validation.constraints.Positive;

public class EmployeeUpdateDto {
	private String name;
	private String department;
	@Positive(message = "Salary must be positive")
	private Double salary;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
}
