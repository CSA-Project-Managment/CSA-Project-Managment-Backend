package com.nighthawk.spring_portfolio.mvc.student;

import org.springframework.data.jpa.repository.JpaRepository;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface StudentJPARepository extends JpaRepository<Student, Long> {
    // Connection
}
