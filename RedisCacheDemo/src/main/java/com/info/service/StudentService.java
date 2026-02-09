package com.info.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.entity.Student;
import com.info.repository.StudentRepository;

@Service
public class StudentService {
	
	
	private StudentRepository repo;
	public StudentService(StudentRepository repo) {
		this.repo = repo;
	}
	
	@CachePut(value = "students", key = "#result.id")
	@CacheEvict(value = "studentList", allEntries = true)
	@Transactional
	public Student save(Student stud) {
		return repo.save(stud);
	}
	
	 @Cacheable(value = "students", key = "#id")
	public Student getStudentById(int id) {
		return repo.findById(id).orElseThrow(()->new RuntimeException("Student Not found with id: "+ id));
	}
	
	 @Cacheable(value = "studentList")
	public List<Student> getAll(){
		return repo.findAll();
	}
}
