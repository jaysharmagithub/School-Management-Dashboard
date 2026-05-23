package com.educloud.assessment.service;

import com.educloud.assessment.dto.TeacherDTO;
import com.educloud.assessment.entity.Teacher;
import com.educloud.assessment.exceptions.ParentAlreadyExistException;
import com.educloud.assessment.exceptions.ParentNotFoundException;
import com.educloud.assessment.exceptions.TeacherNotFoundException;
import com.educloud.assessment.response.PageResponse;

import java.sql.SQLException;
import java.util.List;

public interface ITeacherService {

//     List<Teacher> getTeachers() throws SQLException;

     PageResponse<TeacherDTO> getTeachers(int page, int size, String sortBy, String sortDir) throws SQLException;

     void save(Teacher teacher) throws SQLException;

     void updateTeacher(Long id, TeacherDTO teacherDTO) throws SQLException, TeacherNotFoundException;

     void saveTeacher(TeacherDTO teacherDTO) throws ParentAlreadyExistException, SQLException;

     void deleteTeacher(Long id);
}
