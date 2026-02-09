package com.info.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.entity.Student;
import com.info.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	private StudentService service;
	public StudentController(StudentService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Student> save(@RequestBody Student stud){
		Student dbStudent = service.save(stud);
		return ResponseEntity.status(HttpStatus.CREATED).body(dbStudent);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getAll(){
		return ResponseEntity.ok().body(service.getAll());
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable int id){
		return ResponseEntity.ok().body(service.getStudentById(id));
	}
}
