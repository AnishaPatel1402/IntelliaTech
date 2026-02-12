package com.info.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.info.dto.EmployeeRequestDto;
import com.info.dto.EmployeeResponseDto;
import com.info.dto.EmployeeUpdateDto;

public interface EmployeeService {
	
	public EmployeeResponseDto createEmployee(EmployeeRequestDto emp);
	
	public EmployeeResponseDto updateEmployee(EmployeeUpdateDto emp, int id);
	
	public EmployeeResponseDto deleteEmployee(int id);
	
	public EmployeeResponseDto getEmployeeById(int id);
	
	public Page<EmployeeResponseDto> getAllEmployees(Pageable pageable);

}
