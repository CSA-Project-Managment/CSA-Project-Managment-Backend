package com.nighthawk.spring_portfolio.mvc.student;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {
    
    @Autowired
    private StudentJPARepository studentJPARepository;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Student>> getAllStudents() {
        return ResponseEntity.ok(studentJPARepository.findAll());
    }

    @GetMapping("/find")
    public ResponseEntity<Student> getStudentByCriteria(
            @RequestParam String name, 
            @RequestParam String course, 
            @RequestParam int trimester, 
            @RequestParam int period) {
        
        List<Student> students = studentJPARepository.findByNameCourseTrimesterPeriod(name, course, trimester, period);
        
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(students.get(0));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            Optional<Student> existingStudents = studentJPARepository.findByUsername(student.getUsername());
            if (!existingStudents.isEmpty()) {
                throw new RuntimeException("A student with this GitHub ID already exists.");
            }
            Student createdStudent = studentJPARepository.save(student);
            return ResponseEntity.ok(createdStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudentByUsername(@RequestParam String username) {
        Optional<Student> student = studentJPARepository.findByUsername(username);
        
        if (student.isPresent()) {
            studentJPARepository.deleteById(student.get().getId());  // Delete student by ID
            return ResponseEntity.ok("Student with username '" + username + "' has been deleted.");
        } else {
            return ResponseEntity.status(404).body("Student with username '" + username + "' not found.");
        }
    }

    @GetMapping("/findteam")
    public ResponseEntity<Iterable<Student>> getStudentByCriteria(
            @RequestParam String course, 
            @RequestParam int trimester, 
            @RequestParam int period,
            @RequestParam int table) {
        
        List<Student> students = studentJPARepository.findTeam(course, trimester, period, table);
        
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(students);
        }
    }

    @GetMapping("/findperiod")
    public ResponseEntity<Iterable<Student>> getPeriodByTrimester(
        @RequestParam String course,
        @RequestParam int trimester,
        @RequestParam int period) {
        
        List<Student> students = studentJPARepository.findPeriod(course, trimester, period);

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(students);
        }
    }

    // Change this to use @Getter and studentDto
    @PostMapping("/updatetasks")
    public ResponseEntity<Student> updateTasks(@RequestBody Map<String, Object> requestBody) {
        String username = (String) requestBody.get("username");
        ArrayList<String> tasks = (ArrayList<String>) requestBody.get("tasks");

        Optional<Student> student = studentJPARepository.findByUsername(username);

        if (student.isPresent()) {
            Student student1 = student.get();
            student1.setTasks(new ArrayList<>(tasks));
            studentJPARepository.save(student1);
            return ResponseEntity.ok(student1);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

}
