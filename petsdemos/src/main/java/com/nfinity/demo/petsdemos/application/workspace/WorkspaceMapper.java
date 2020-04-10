package com.nfinity.demo.petsdemos.application.workspace;

import org.mapstruct.Mapper;
import com.nfinity.demo.petsdemos.application.workspace.dto.*;
import com.nfinity.demo.petsdemos.domain.model.WorkspaceEntity;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {

   WorkspaceEntity createWorkspaceInputToWorkspaceEntity(CreateWorkspaceInput workspaceDto);
   
   CreateWorkspaceOutput workspaceEntityToCreateWorkspaceOutput(WorkspaceEntity entity);

   WorkspaceEntity updateWorkspaceInputToWorkspaceEntity(UpdateWorkspaceInput workspaceDto);

   UpdateWorkspaceOutput workspaceEntityToUpdateWorkspaceOutput(WorkspaceEntity entity);

   FindWorkspaceByIdOutput workspaceEntityToFindWorkspaceByIdOutput(WorkspaceEntity entity);


}
