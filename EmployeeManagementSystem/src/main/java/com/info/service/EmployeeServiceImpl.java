package com.info.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.dto.EmployeeRequestDto;
import com.info.dto.EmployeeResponseDto;
import com.info.dto.EmployeeUpdateDto;
import com.info.entity.Employee;
import com.info.exception.ResourceNotFoundException;
import com.info.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;
	private PasswordEncoder encoder;

	public EmployeeServiceImpl(EmployeeRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@Override
	@Transactional
	public EmployeeResponseDto createEmployee(EmployeeRequestDto empReq) {
		Employee emp = new Employee();
		emp.setName(empReq.getName());
		emp.setEmail(empReq.getEmail());
		emp.setDepartment(empReq.getDepartment());
		emp.setSalary(empReq.getSalary());

		String encryptedPassword = encoder.encode(empReq.getPassword());
		emp.setPassword(encryptedPassword);

		emp = repository.save(emp);

		EmployeeResponseDto empRes = new EmployeeResponseDto();
		empRes.setId(emp.getId());
		empRes.setName(emp.getName());
		empRes.setDepartment(emp.getDepartment());
		empRes.setSalary(emp.getSalary());

		return empRes;
	}

	@Override
	@Transactional
	public EmployeeResponseDto updateEmployee(EmployeeUpdateDto empReq, int id) {
		Employee dbEmployee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));

		dbEmployee.setName(empReq.getName());
		dbEmployee.setDepartment(empReq.getDepartment());
		dbEmployee.setSalary(empReq.getSalary());

		repository.save(dbEmployee);

		EmployeeResponseDto empRes = new EmployeeResponseDto();
		empRes.setId(dbEmployee.getId());
		empRes.setName(dbEmployee.getName());
		empRes.setDepartment(dbEmployee.getDepartment());
		empRes.setSalary(dbEmployee.getSalary());

		return empRes;
	}

	@Override
	@Transactional
	public EmployeeResponseDto deleteEmployee(int id) {
		Employee dbEmployee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
		EmployeeResponseDto empRes = new EmployeeResponseDto();
		empRes.setId(dbEmployee.getId());
		empRes.setName(dbEmployee.getName());
		empRes.setDepartment(dbEmployee.getDepartment());
		empRes.setSalary(dbEmployee.getSalary());

		repository.delete(dbEmployee);

		return empRes;
	}

	@Override
	public EmployeeResponseDto getEmployeeById(int id) {
		Employee dbEmployee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
		EmployeeResponseDto empRes = new EmployeeResponseDto();
		empRes.setId(dbEmployee.getId());
		empRes.setName(dbEmployee.getName());
		empRes.setDepartment(dbEmployee.getDepartment());
		empRes.setSalary(dbEmployee.getSalary());

		return empRes;
	}

	@Override
	public Page<EmployeeResponseDto> getAllEmployees(Pageable pageable) {
		Page<Employee> employeePage = repository.findAll(pageable);

		return employeePage.map(emp -> {
			EmployeeResponseDto res = new EmployeeResponseDto();
			res.setId(emp.getId());
			res.setName(emp.getName());
			res.setDepartment(emp.getDepartment());
			res.setSalary(emp.getSalary());
			return res;
		});
	}

	@Override
	public List<EmployeeResponseDto> getHighestPaidEmployees() {
		List<Employee> employees = repository.getHighestPaidEmployees();

		return employees.stream().map(emp -> {
			EmployeeResponseDto dto = new EmployeeResponseDto();
			dto.setId(emp.getId());
			dto.setName(emp.getName());
			dto.setDepartment(emp.getDepartment());
			dto.setSalary(emp.getSalary());
			return dto;
		}).collect(Collectors.toList());
	}

	public List<EmployeeResponseDto> getEmployeeByDepartment(String department) {
		List<Employee> employees = repository.getEmployeeByDepartment(department);

		return employees.stream().map(emp -> {
			EmployeeResponseDto dto = new EmployeeResponseDto();
			dto.setId(emp.getId());
			dto.setName(emp.getName());
			dto.setDepartment(emp.getDepartment());
			dto.setSalary(emp.getSalary());
			return dto;
		}).collect(Collectors.toList());
	}

	public List<EmployeeResponseDto> getEmployeeSalaryGreaterThan(Double salary) {
		List<Employee> employees = repository.findBySalaryGreaterThan(salary);

		return employees.stream().map(emp -> {
			EmployeeResponseDto dto = new EmployeeResponseDto();
			dto.setId(emp.getId());
			dto.setName(emp.getName());
			dto.setDepartment(emp.getDepartment());
			dto.setSalary(emp.getSalary());
			return dto;
		}).collect(Collectors.toList());
	}
	
	
	public List<EmployeeResponseDto> getEmployeesWithAvgSalary(){
		List<Employee> employees = repository.getEmployeesAboveAverageSalary();

		return employees.stream().map(emp -> {
			EmployeeResponseDto dto = new EmployeeResponseDto();
			dto.setId(emp.getId());
			dto.setName(emp.getName());
			dto.setDepartment(emp.getDepartment());
			dto.setSalary(emp.getSalary());
			return dto;
		}).collect(Collectors.toList());
	}

}