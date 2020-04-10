package com.nfinity.demo.petsdemos.domain.workspace;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.nfinity.demo.petsdemos.domain.model.WorkspaceEntity;
import com.nfinity.demo.petsdemos.domain.irepository.IWorkspaceRepository;
import com.querydsl.core.types.Predicate;

@Repository
public class WorkspaceManager implements IWorkspaceManager {

    @Autowired
    IWorkspaceRepository  _workspaceRepository;
    
	public WorkspaceEntity create(WorkspaceEntity workspace) {

		return _workspaceRepository.save(workspace);
	}

	public void delete(WorkspaceEntity workspace) {

		_workspaceRepository.delete(workspace);	
	}

	public WorkspaceEntity update(WorkspaceEntity workspace) {

		return _workspaceRepository.save(workspace);
	}

	public WorkspaceEntity findById(Long workspaceId) {
    	Optional<WorkspaceEntity> dbWorkspace= _workspaceRepository.findById(workspaceId);
		if(dbWorkspace.isPresent()) {
			WorkspaceEntity existingWorkspace = dbWorkspace.get();
		    return existingWorkspace;
		} else {
		    return null;
		}

	}

	public Page<WorkspaceEntity> findAll(Predicate predicate, Pageable pageable) {

		return _workspaceRepository.findAll(predicate,pageable);
	}
}
