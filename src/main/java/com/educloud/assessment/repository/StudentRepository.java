package com.educloud.assessment.repository;

import com.educloud.assessment.entity.Student;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByRollNumber(String rollNumber);
}
