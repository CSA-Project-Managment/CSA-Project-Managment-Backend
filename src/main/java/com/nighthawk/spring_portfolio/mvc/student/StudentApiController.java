package com.nighthawk.spring_portfolio.mvc.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {
    
    @Autowired
    private Student.StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Student>> getAllFlights() {
        return ResponseEntity.ok(studentService.findAll());
    }

}