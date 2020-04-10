package com.nfinity.demo.petsdemos.domain.workspace;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.validation.constraints.Positive;
import com.nfinity.demo.petsdemos.domain.model.WorkspaceEntity;

public interface IWorkspaceManager {
    // CRUD Operations
    WorkspaceEntity create(WorkspaceEntity workspace);

    void delete(WorkspaceEntity workspace);

    WorkspaceEntity update(WorkspaceEntity workspace);

    WorkspaceEntity findById(Long id);
	
    Page<WorkspaceEntity> findAll(Predicate predicate, Pageable pageable);
}
