package com.info.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class EmployeeRequestDto {
	
	@NotBlank(message = "Name cannot be empty")
	@Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters long")
	private String name;
	
	private String department;
	
	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid Email format")
	private String email;
	
	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters long")
	private String password;
	
	@NotNull(message = "Salary is required")
	@Positive(message = "Salary must be greater than zero")
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	
}
