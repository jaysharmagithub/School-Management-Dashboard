package com.educloud.assessment.service;

import com.educloud.assessment.dto.ParentDTO;
import com.educloud.assessment.entity.Parent;
import com.educloud.assessment.exceptions.ParentAlreadyExistException;
import com.educloud.assessment.exceptions.ParentNotFoundException;
import com.educloud.assessment.response.PageResponse;

import java.sql.SQLException;
import java.util.List;

public interface IParentService {
    public PageResponse<ParentDTO> getParents(int page, int size, String sortBy, String sortDir) throws SQLException;

    public void save(Parent parent) throws SQLException;

    public void updateParent(Long id, ParentDTO parentDTO) throws SQLException, ParentNotFoundException;

    public void saveParent(ParentDTO parentDTO) throws SQLException, ParentAlreadyExistException;

    void deleteParent(Long id) throws SQLException;
}
