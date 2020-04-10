package com.nfinity.demo.petsdemos.application.workspace;

import com.nfinity.demo.petsdemos.application.workspace.dto.*;
import com.nfinity.demo.petsdemos.domain.workspace.IWorkspaceManager;
import com.nfinity.demo.petsdemos.domain.model.QWorkspaceEntity;
import com.nfinity.demo.petsdemos.domain.model.WorkspaceEntity;
import com.nfinity.demo.petsdemos.commons.search.*;
import com.nfinity.demo.petsdemos.commons.logging.LoggingHelper;
import com.querydsl.core.BooleanBuilder;

import java.util.*;
import org.springframework.cache.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

@Service
@Validated
public class WorkspaceAppService implements IWorkspaceAppService {

    static final int case1=1;
	static final int case2=2;
	static final int case3=3;
	
	@Autowired
	private IWorkspaceManager _workspaceManager;

	@Autowired
	private WorkspaceMapper mapper;
	
	@Autowired
	private LoggingHelper logHelper;

    @Transactional(propagation = Propagation.REQUIRED)
	public CreateWorkspaceOutput create(CreateWorkspaceInput input) {

		WorkspaceEntity workspace = mapper.createWorkspaceInputToWorkspaceEntity(input);
		WorkspaceEntity createdWorkspace = _workspaceManager.create(workspace);
		
		return mapper.workspaceEntityToCreateWorkspaceOutput(createdWorkspace);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@CacheEvict(value="Workspace", key = "#p0")
	public UpdateWorkspaceOutput update(Long  workspaceId, UpdateWorkspaceInput input) {

		WorkspaceEntity workspace = mapper.updateWorkspaceInputToWorkspaceEntity(input);
		
		WorkspaceEntity updatedWorkspace = _workspaceManager.update(workspace);
		
		return mapper.workspaceEntityToUpdateWorkspaceOutput(updatedWorkspace);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@CacheEvict(value="Workspace", key = "#p0")
	public void delete(Long workspaceId) {

		WorkspaceEntity existing = _workspaceManager.findById(workspaceId) ; 
		_workspaceManager.delete(existing);
		
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Cacheable(value = "Workspace", key = "#p0")
	public FindWorkspaceByIdOutput findById(Long workspaceId) {

		WorkspaceEntity foundWorkspace = _workspaceManager.findById(workspaceId);
		if (foundWorkspace == null)  
			return null ; 
 	   
 	    FindWorkspaceByIdOutput output=mapper.workspaceEntityToFindWorkspaceByIdOutput(foundWorkspace); 
		return output;
	}
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Cacheable(value = "Workspace")
	public List<FindWorkspaceByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception  {

		Page<WorkspaceEntity> foundWorkspace = _workspaceManager.findAll(search(search), pageable);
		List<WorkspaceEntity> workspaceList = foundWorkspace.getContent();
		Iterator<WorkspaceEntity> workspaceIterator = workspaceList.iterator(); 
		List<FindWorkspaceByIdOutput> output = new ArrayList<>();

		while (workspaceIterator.hasNext()) {
			output.add(mapper.workspaceEntityToFindWorkspaceByIdOutput(workspaceIterator.next()));
		}
		return output;
	}
	
	public BooleanBuilder search(SearchCriteria search) throws Exception {

		QWorkspaceEntity workspace= QWorkspaceEntity.workspaceEntity;
		if(search != null) {
			Map<String,SearchFields> map = new HashMap<>();
			for(SearchFields fieldDetails: search.getFields())
			{
				map.put(fieldDetails.getFieldName(),fieldDetails);
			}
			List<String> keysList = new ArrayList<String>(map.keySet());
			checkProperties(keysList);
			return searchKeyValuePair(workspace, map,search.getJoinColumns());
		}
		return null;
	}
	
	public void checkProperties(List<String> list) throws Exception  {
		for (int i = 0; i < list.size(); i++) {
			if(!(
				list.get(i).replace("%20","").trim().equals("id") ||
				list.get(i).replace("%20","").trim().equals("name")
			)) 
			{
			 throw new Exception("Wrong URL Format: Property " + list.get(i) + " not found!" );
			}
		}
	}
	
	public BooleanBuilder searchKeyValuePair(QWorkspaceEntity workspace, Map<String,SearchFields> map,Map<String,String> joinColumns) {
		BooleanBuilder builder = new BooleanBuilder();
        
		for (Map.Entry<String, SearchFields> details : map.entrySet()) {
            if(details.getKey().replace("%20","").trim().equals("name")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(workspace.name.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(workspace.name.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(workspace.name.ne(details.getValue().getSearchValue()));
			}
		}
		return builder;
	}
	
	
    
	
}


