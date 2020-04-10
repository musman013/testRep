package com.nfinity.demo.petsdemos.restcontrollers;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.nfinity.demo.petsdemos.commons.search.SearchCriteria;
import com.nfinity.demo.petsdemos.commons.search.SearchUtils;
import com.nfinity.demo.petsdemos.commons.application.OffsetBasedPageRequest;
import com.nfinity.demo.petsdemos.commons.domain.EmptyJsonResponse;
import com.nfinity.demo.petsdemos.application.workspace.WorkspaceAppService;
import com.nfinity.demo.petsdemos.application.workspace.dto.*;
import java.util.List;
import java.util.Map;
import com.nfinity.demo.petsdemos.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

	@Autowired
	private WorkspaceAppService _workspaceAppService;

	@Autowired
	private LoggingHelper logHelper;

	@Autowired
	private Environment env;
	
	
    
    public WorkspaceController(WorkspaceAppService workspaceAppService,
	 LoggingHelper helper) {
		super();
		this._workspaceAppService = workspaceAppService;
		this.logHelper = helper;
	}

    @PreAuthorize("hasAnyAuthority('WORKSPACEENTITY_CREATE')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CreateWorkspaceOutput> create(@RequestBody @Valid CreateWorkspaceInput workspace) {
		CreateWorkspaceOutput output=_workspaceAppService.create(workspace);
		return new ResponseEntity(output, HttpStatus.OK);
	}
   
	// ------------ Delete workspace ------------
	@PreAuthorize("hasAnyAuthority('WORKSPACEENTITY_DELETE')")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
    FindWorkspaceByIdOutput output = _workspaceAppService.findById(Long.valueOf(id));
	if (output == null) {
		logHelper.getLogger().error("There does not exist a workspace with a id=%s", id);
		throw new EntityNotFoundException(
			String.format("There does not exist a workspace with a id=%s", id));
	}
    _workspaceAppService.delete(Long.valueOf(id));
    }
	
	// ------------ Update workspace ------------
    @PreAuthorize("hasAnyAuthority('WORKSPACEENTITY_UPDATE')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UpdateWorkspaceOutput> update(@PathVariable String id, @RequestBody @Valid UpdateWorkspaceInput workspace) {
	    FindWorkspaceByIdOutput currentWorkspace = _workspaceAppService.findById(Long.valueOf(id));
			
		if (currentWorkspace == null) {
			logHelper.getLogger().error("Unable to update. Workspace with id {} not found.", id);
			return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
		}
		
	    return new ResponseEntity(_workspaceAppService.update(Long.valueOf(id),workspace), HttpStatus.OK);
	}
    @PreAuthorize("hasAnyAuthority('WORKSPACEENTITY_READ')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FindWorkspaceByIdOutput> findById(@PathVariable String id) {
    FindWorkspaceByIdOutput output = _workspaceAppService.findById(Long.valueOf(id));
		if (output == null) {
			return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity(output, HttpStatus.OK);
	}
    
    @PreAuthorize("hasAnyAuthority('WORKSPACEENTITY_READ')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity find(@RequestParam(value="search", required=false) String search, @RequestParam(value = "offset", required=false) String offset, @RequestParam(value = "limit", required=false) String limit, Sort sort) throws Exception {
		if (offset == null) { offset = env.getProperty("fastCode.offset.default"); }
		if (limit == null) { limit = env.getProperty("fastCode.limit.default"); }

		Pageable Pageable = new OffsetBasedPageRequest(Integer.parseInt(offset), Integer.parseInt(limit), sort);
		SearchCriteria searchCriteria = SearchUtils.generateSearchCriteriaObject(search);
		
		return ResponseEntity.ok(_workspaceAppService.find(searchCriteria,Pageable));
	}


}

