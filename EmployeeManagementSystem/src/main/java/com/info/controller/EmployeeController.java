package com.info.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.dto.EmployeeRequestDto;
import com.info.dto.EmployeeResponseDto;
import com.info.dto.EmployeeUpdateDto;
import com.info.service.EmployeeServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeServiceImpl service;
	public EmployeeController(EmployeeServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto empReq) {
        EmployeeResponseDto response = service.createEmployee(empReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable int id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getEmployeeById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> deleteEmployee(@PathVariable int id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteEmployee(id));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable int id, @RequestBody EmployeeUpdateDto empReq) {
	    EmployeeResponseDto updatedEmployee = service.updateEmployee(empReq, id);
	    return ResponseEntity.ok(updatedEmployee);
	}
	
	@GetMapping
	public ResponseEntity<Page<EmployeeResponseDto>> getAllEmployees(
	        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
	    
	    Page<EmployeeResponseDto> employees = service.getAllEmployees(pageable);
	    return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/highest-salary")
	public ResponseEntity<List<EmployeeResponseDto>> getHighestPaid() {
	    List<EmployeeResponseDto> list = service.getHighestPaidEmployees();
	    
	    if (list.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    
	    return ResponseEntity.ok(list);
	}
}
