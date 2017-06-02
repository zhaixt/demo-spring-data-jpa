package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findById(Long id);
}
