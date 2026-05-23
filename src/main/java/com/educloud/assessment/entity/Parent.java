package com.educloud.assessment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
//@NoArgsConstructor
public class Parent extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private String Relation;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String occupation;

    private String address;

    @ManyToMany(mappedBy = "parents")
    private List<Student> students;
}
