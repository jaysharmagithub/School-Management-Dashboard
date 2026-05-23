package com.educloud.assessment.repository;


import com.educloud.assessment.entity.Teacher;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByEmployeeId( String employeeId);

    boolean existsByEmail(String email);
}
