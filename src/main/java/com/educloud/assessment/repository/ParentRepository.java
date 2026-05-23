package com.educloud.assessment.repository;

import com.educloud.assessment.entity.Parent;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    boolean existsByEmail(String email);
}
