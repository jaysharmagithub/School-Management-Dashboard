package com.educloud.assessment.service;

import com.educloud.assessment.dto.StudentDTO;
import com.educloud.assessment.entity.Student;
import com.educloud.assessment.exceptions.ParentNotFoundException;
import com.educloud.assessment.exceptions.StudentAlreadyExistException;
import com.educloud.assessment.exceptions.StudentNotFoundException;
import com.educloud.assessment.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.SQLException;
import java.util.List;

public interface IStudentService {

     PageResponse<StudentDTO> getStudents(int page, int size, String sortBy, String sortDir) throws SQLException;
     void save(Student student) throws SQLException;
     void updateStudent(Long id, StudentDTO studentDTO) throws SQLException, StudentNotFoundException;
     void saveStudent(StudentDTO studentDTO) throws SQLException, StudentAlreadyExistException;

     void deleteStudent(Long id);
}
