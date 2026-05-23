package com.educloud.assessment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
//@NoArgsConstructor
public class Teacher extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String employeeId;

    private String email;

    private String phone;

    private String subject;

    private Integer experienceYears;

    private LocalDate joiningDate;

    private String qualification;

    private String address;

    @OneToMany(mappedBy = "teacher")
    private List<Student> students;


}