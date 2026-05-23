package com.educloud.assessment.dto;

import com.educloud.assessment.entity.Teacher;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
//@NoArgsConstructor
public class TeacherDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50)
    private String firstName;

    private String lastName;

    @NotBlank
    private String employeeId;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}$")
    private String phone;

    @NotBlank
    private String subject;

    @Min(value = 0)
    @Max(value = 50)
    private Integer experienceYears;

    @NotBlank
    private String qualification;

    @NotBlank
    private String address;

    @NotNull
    private LocalDate joiningDate;

    public static Teacher toTeacher(TeacherDTO teacherDTO) {
        if (teacherDTO == null) return null;

        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setEmployeeId(teacherDTO.getEmployeeId());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setPhone(teacherDTO.getPhone());
        teacher.setSubject(teacherDTO.getSubject());
        teacher.setExperienceYears(teacherDTO.getExperienceYears());
        teacher.setQualification(teacherDTO.getQualification());
         teacher.setJoiningDate(teacherDTO.getJoiningDate());
         teacher.setAddress(teacherDTO.getAddress());

        return teacher;
    }
}
