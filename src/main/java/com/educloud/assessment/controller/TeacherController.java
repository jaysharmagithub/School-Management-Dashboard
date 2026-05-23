package com.educloud.assessment.controller;

import com.educloud.assessment.dto.ParentDTO;
import com.educloud.assessment.dto.StudentDTO;
import com.educloud.assessment.dto.TeacherDTO;
import com.educloud.assessment.entity.Parent;
import com.educloud.assessment.entity.Teacher;
import com.educloud.assessment.response.PageResponse;
import com.educloud.assessment.response.TableResponse;
import com.educloud.assessment.service.ParentService;
import com.educloud.assessment.service.TeacherService;
import com.educloud.assessment.util.DTOMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.TE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/metadata")
    public ResponseEntity<List<Map<String, Object>>> getFieldNames(){
        return ResponseEntity.ok(DTOMapper.getColumns(TeacherDTO.class));
    }

    @GetMapping
    public ResponseEntity<TableResponse<TeacherDTO>> getTeachers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws SQLException {

        PageResponse<TeacherDTO> res = teacherService.getTeachers(page, size, "id", "asc");

        return ResponseEntity.ok(
                TableResponse.<TeacherDTO>builder()
                        .columns(DTOMapper.getColumns(TeacherDTO.class))
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
    public ResponseEntity<Void> addTeacher(@RequestBody TeacherDTO dto) throws SQLException {
        teacherService.saveTeacher(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTeacher(
            @PathVariable Long id,
            @RequestBody TeacherDTO dto
    ) throws SQLException {

        teacherService.updateTeacher(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) throws SQLException {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
