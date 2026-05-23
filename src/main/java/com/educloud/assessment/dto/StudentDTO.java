package com.educloud.assessment.dto;

import com.educloud.assessment.entity.Student;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
//@RequiredArgsConstructor
//@NoArgsConstructor

public class StudentDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @NotBlank(message = "Roll number required")
    private String rollNumber;

    @Past(message = "DOB must be in past")
    private LocalDate dateOfBirth;

    @NotBlank
    private String gender;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    @Size(max = 255)
    private String address;

    @NotBlank
    private String grade;

    @NotBlank
    private String section;

    @NotBlank
    private String parentName;

    @NotBlank
    private String relation;

    public StudentDTO(String firstName, String lastName, String rollNumber, LocalDate dateOfBirth, String gender, String email, String phone, String address, String grade, String section, String parentName, String relation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNumber = rollNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.grade = grade;
        this.section = section;
        this.parentName = parentName;
        this.relation = relation;
    }

    public static Student toStudent(StudentDTO dto) {
        if (dto == null) throw new  IllegalArgumentException("Request can not be null. Contact Support Team");


        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setRollNumber(dto.getRollNumber());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setAddress(dto.getAddress());
        student.setGrade(dto.getGrade());
        student.setSection(dto.getSection());
        return student;
    }

}
