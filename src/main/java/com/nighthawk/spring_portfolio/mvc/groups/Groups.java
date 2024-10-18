package com.nighthawk.spring_portfolio.mvc.groups;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
@AllArgsConstructor
@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object.
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int trimester;
    
    @ElementCollection
    private List<String> students;

    private String course;

    public Groups(String name, List<String> students, String course, int trimester) {
        this.name = name;
        this.students = students;
        this.course = course;
        this.trimester = trimester;
    }
}
