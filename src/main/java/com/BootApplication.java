package com;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Student;
import com.service.StudentService;


/**
 * 
 * @author liangz
 *
 */
@SpringBootApplication
@RestController
public class BootApplication {

    
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello ,spring boot!";
    }
    
	@Autowired
    private StudentService studentService;

    @RequestMapping("/id/{id}")
    List<Student> findById(@PathVariable Long id) {
        return studentService.findStudentNameById(id);
    }



}
