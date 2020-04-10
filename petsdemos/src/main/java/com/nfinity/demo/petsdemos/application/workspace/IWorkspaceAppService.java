package com.nfinity.demo.petsdemos.application.workspace;

import java.util.List;
import javax.validation.constraints.Positive;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.nfinity.demo.petsdemos.commons.search.SearchCriteria;
import com.nfinity.demo.petsdemos.application.workspace.dto.*;

@Service
public interface IWorkspaceAppService {

	CreateWorkspaceOutput create(CreateWorkspaceInput workspace);

    void delete(Long id);

    UpdateWorkspaceOutput update(Long id, UpdateWorkspaceInput input);

    FindWorkspaceByIdOutput findById(Long id);

    List<FindWorkspaceByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;

}
