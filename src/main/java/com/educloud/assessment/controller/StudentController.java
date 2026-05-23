package com.educloud.assessment.controller;

import com.educloud.assessment.dto.ParentDTO;
import com.educloud.assessment.dto.StudentDTO;
import com.educloud.assessment.entity.Parent;
import com.educloud.assessment.response.PageResponse;
import com.educloud.assessment.response.TableResponse;
import com.educloud.assessment.service.ParentService;
import com.educloud.assessment.service.StudentService;
import com.educloud.assessment.util.DTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("/*")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;



    @GetMapping("/metadata")
    public ResponseEntity<List<Map<String, Object>>> getFieldNames(){
        return ResponseEntity.ok(DTOMapper.getColumns(StudentDTO.class));
    }

    @GetMapping
    public ResponseEntity<TableResponse<StudentDTO>> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws SQLException {

        PageResponse<StudentDTO> res = studentService.getStudents(page, size, "id", "asc");

        return ResponseEntity.ok(
                TableResponse.<StudentDTO>builder()
                        .columns(DTOMapper.getColumns(ParentDTO.class))
                        .data(res.getContent())
                        .page(res.getPage())
                        .size(res.getSize())
                        .totalElements(res.getTotalElements())
                        .totalPages(res.getTotalPages())
                        .last(res.isLast())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody StudentDTO dto) throws SQLException {
        studentService.saveStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDTO dto
    ) throws SQLException {

        studentService.updateStudent(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) throws SQLException {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
