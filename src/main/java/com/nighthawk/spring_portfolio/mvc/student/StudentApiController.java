package com.nighthawk.spring_portfolio.mvc.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/find")
    public ResponseEntity<Student> getStudentByCriteria(
            @RequestParam String name, 
            @RequestParam String course, 
            @RequestParam int trimester, 
            @RequestParam int period) {
        
        List<Student> students = studentService.findByNameCourseTrimesterPeriod(name, course, trimester, period);
        
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(students.get(0));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            Student createdStudent = studentService.createStudent(student);
            return ResponseEntity.ok(createdStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}