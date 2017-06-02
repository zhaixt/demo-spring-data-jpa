package com.controllter;

import com.model.Student;
import com.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/stu")
public class StudentController {
	@Autowired
    private StudentService studentService;

    @RequestMapping("/id/{id}")
    List<Student> findById(@PathVariable Long id) {
        return studentService.findStudentNameById(id);
    }

}
