package com.educloud.assessment.service;

import com.educloud.assessment.dto.StudentDTO;
import com.educloud.assessment.dto.TeacherDTO;
import com.educloud.assessment.entity.Teacher;
import com.educloud.assessment.exceptions.ParentAlreadyExistException;
import com.educloud.assessment.exceptions.ParentNotFoundException;
import com.educloud.assessment.exceptions.StudentNotFoundException;
import com.educloud.assessment.exceptions.TeacherNotFoundException;
import com.educloud.assessment.repository.TeacherRepository;
import com.educloud.assessment.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService{
    private final TeacherRepository teacherRepository;

//    public List<Teacher> getTeachers() throws SQLException{
//        return teacherRepository.findAll();
//    }

    public PageResponse<TeacherDTO> getTeachers(int page, int size, String sortBy, String sortDir) throws SQLException {

        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);

        List<TeacherDTO> dtoList = teacherPage.getContent()
                .stream()
                .map(this::mapToDTO)
                .toList();

        return PageResponse.<TeacherDTO>builder()
                .content(dtoList)
                .page(teacherPage.getNumber())
                .size(teacherPage.getSize())
                .totalElements(teacherPage.getTotalElements())
                .totalPages(teacherPage.getTotalPages())
                .last(teacherPage.isLast())
                .build();
    }

    private TeacherDTO mapToDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setEmployeeId(teacher.getEmployeeId());
        dto.setEmail(teacher.getEmail());
        dto.setPhone(teacher.getPhone());
        dto.setSubject(teacher.getSubject());
        dto.setExperienceYears(teacher.getExperienceYears());
        dto.setQualification(teacher.getQualification());
        return dto;
    }

    public void save(Teacher teacher) throws SQLException{
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Long id, TeacherDTO teacherDTO) throws SQLException, TeacherNotFoundException {
      Teacher existing =  teacherRepository
                .findById(id)
                .orElseThrow(() -> new ParentNotFoundException("Student not found by given Id"));
      Teacher teacherToSaved = TeacherDTO.toTeacher(teacherDTO);
      teacherToSaved.setId(existing.getId());
      teacherRepository.save(teacherToSaved);

    }

    public void saveTeacher(TeacherDTO teacherDTO) throws ParentAlreadyExistException, SQLException {
        if(teacherRepository.existsByEmployeeId(teacherDTO.getEmployeeId()))
            throw new ParentAlreadyExistException("Parent already Present By given EmployeeId");
        if(teacherRepository.existsByEmail(teacherDTO.getEmail()))
            throw new ParentAlreadyExistException("Parent already Present By given email");
        teacherRepository.save(TeacherDTO.toTeacher(teacherDTO));
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
