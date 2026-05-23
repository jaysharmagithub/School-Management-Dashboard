package com.educloud.assessment.controller;


import com.educloud.assessment.dto.ParentDTO;
import com.educloud.assessment.response.PageResponse;
import com.educloud.assessment.response.TableResponse;
import com.educloud.assessment.service.ParentService;
import com.educloud.assessment.util.DTOMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
@CrossOrigin("/*")
public class ParentController {
    private final ParentService parentService;

    @GetMapping("/metadata")
    public ResponseEntity<List<Map<String, Object>>> getFieldNames() throws Exception {
        return ResponseEntity.ok(DTOMapper.getColumns(ParentDTO.class));
    }

@GetMapping
public ResponseEntity<TableResponse<ParentDTO>> getParents(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) throws SQLException {

    PageResponse<ParentDTO> res = parentService.getParents(page, size, "id", "asc");

    return ResponseEntity.ok(
            TableResponse.<ParentDTO>builder()
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
    public ResponseEntity<Void> addParent(@RequestBody @Valid ParentDTO dto) throws SQLException {
        parentService.saveParent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateParent(
            @PathVariable Long id,
            @RequestBody ParentDTO dto
    ) throws SQLException {

        parentService.updateParent(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) throws SQLException {
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
}
