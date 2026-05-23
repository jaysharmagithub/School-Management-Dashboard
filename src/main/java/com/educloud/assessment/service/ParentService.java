package com.educloud.assessment.service;

import com.educloud.assessment.dto.ParentDTO;
import com.educloud.assessment.dto.StudentDTO;
import com.educloud.assessment.entity.Parent;
import com.educloud.assessment.entity.Student;
import com.educloud.assessment.entity.Teacher;
import com.educloud.assessment.exceptions.ParentAlreadyExistException;
import com.educloud.assessment.exceptions.ParentNotFoundException;
import com.educloud.assessment.repository.ParentRepository;
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
public class ParentService implements IParentService {
    private final ParentRepository parentRepository;

    public PageResponse<ParentDTO> getParents(int page, int size, String sortBy, String sortDir) throws SQLException {

        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Parent> studentPage = parentRepository.findAll(pageable);

        List<ParentDTO> dtoList = studentPage.getContent().stream()
                .map(this::mapToDTO)
                .toList();

        return PageResponse.<ParentDTO>builder()
                .content(dtoList)
                .page(studentPage.getNumber())
                .size(studentPage.getSize())
                .totalElements(studentPage.getTotalElements())
                .totalPages(studentPage.getTotalPages())
                .last(studentPage.isLast())
                .build();
    }

    private ParentDTO mapToDTO(Parent parent) {
        ParentDTO dto = new ParentDTO();
        dto.setFirstName(parent.getFirstName());
        dto.setLastName(parent.getLastName());
        dto.setRelation(parent.getRelation());
        dto.setEmail(parent.getEmail());
        dto.setPhone(parent.getPhone());
        dto.setOccupation(parent.getOccupation());
        dto.setAddress(parent.getAddress());
        return dto;
    }


//    @Override
//    public List<Parent> getParents() throws SQLException {
//        return parentRepository
//                .findAll();
//    }

    @Override
    public void save(Parent parent) throws SQLException {
         parentRepository
                 .save(parent);
    }

    @Override
    public void updateParent(Long id, ParentDTO parentDTO) throws SQLException,ParentNotFoundException {
       Parent p = parentRepository
                .findById(id)
                .orElseThrow(() -> new ParentNotFoundException("Parent not found by given Id"));
       Parent parentToSaved = ParentDTO.toParent(parentDTO);
       parentToSaved.setId(p.getId());
       parentRepository.save(parentToSaved);

    }

    @Override
    public void saveParent(ParentDTO parentDTO) throws SQLException, ParentAlreadyExistException {
        if(parentRepository.existsByEmail(parentDTO.getEmail()))
            throw new ParentAlreadyExistException("Parent already Present By given email");
        parentRepository.save(ParentDTO.toParent(parentDTO));
    }

    @Override
    public void deleteParent(Long id) throws SQLException {
        parentRepository.deleteById(id);
    }
}
