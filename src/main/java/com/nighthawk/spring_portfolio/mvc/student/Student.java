package com.nighthawk.spring_portfolio.mvc.student;


import java.util.ArrayList;

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
@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.


public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String username;
    private int table;
    private String course;

    public Student(String name, String username, int table, String course) {
        this.name = name;
        this.username = username;
        this.table = table;
        this.course = course;
    }

    public static Student createStudent(String name, String username, int table, String course) {
        Student student = new Student();
        student.setName(name);
        student.setUsername(username);
        student.setTable(table);
        student.setCourse(course);

        return student;
    }
    
        public static Student[] init() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(createStudent("Akhil Singamneni", "Akhil353", 4, "CSA"));
        students.add(createStudent("Srinivas Nampalli", "srininampalli", 4, "CSA"));
        students.add(createStudent("Aditya Samavedam", "adityasamavedam", 4, "CSA"));
        students.add(createStudent("Nitin Balaji", "nitinsandiego", 4, "CSA"));
        return students.toArray(new Student[0]);
    }

    /** Static method to print Person objects from an array
     * @param args, not used
     */
    public static void main(String[] args) {
        // obtain Person from initializer
        Student students[] = init();

        // iterate using "enhanced for loop"
        for( Student student : students) {
            System.out.println(student);  // print object
        }
    }

}