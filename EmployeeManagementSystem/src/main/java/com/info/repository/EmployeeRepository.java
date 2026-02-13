package com.info.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.info.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	@Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(salary) FROM Employee)")
    List<Employee> getHighestPaidEmployees();
	
	List<Employee> getEmployeeByDepartment(String department);
	
	List<Employee> findBySalaryGreaterThan(Double salary);
	
	@Query("SELECT e FROM Employee e WHERE e.salary >= (SELECT AVG(e2.salary) FROM Employee e2)")
	List<Employee> getEmployeesAboveAverageSalary();
}
                                                                                                                    