package com.service;

import java.util.List;

import com.model.Student;

public interface StudentService {
	public List<Student> findStudentNameById(Long id);
}
