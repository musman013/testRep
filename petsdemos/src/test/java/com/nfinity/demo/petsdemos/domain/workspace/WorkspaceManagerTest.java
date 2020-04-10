package com.nfinity.demo.petsdemos.domain.workspace;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nfinity.demo.petsdemos.domain.model.WorkspaceEntity;
import com.nfinity.demo.petsdemos.domain.irepository.IWorkspaceRepository;
import com.nfinity.demo.petsdemos.commons.logging.LoggingHelper;
import com.querydsl.core.types.Predicate;

@RunWith(SpringJUnit4ClassRunner.class)
public class WorkspaceManagerTest {

	@InjectMocks
	WorkspaceManager _workspaceManager;
	
	@Mock
	IWorkspaceRepository  _workspaceRepository;
	@Mock
    private Logger loggerMock;
   
	@Mock
	private LoggingHelper logHelper;
	
	private static Long ID=15L;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(_workspaceManager);
		when(logHelper.getLogger()).thenReturn(loggerMock);
		doNothing().when(loggerMock).error(anyString());
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void findWorkspaceById_IdIsNotNullAndIdExists_ReturnWorkspace() {
		WorkspaceEntity workspace =mock(WorkspaceEntity.class);

        Optional<WorkspaceEntity> dbWorkspace = Optional.of((WorkspaceEntity) workspace);
		Mockito.<Optional<WorkspaceEntity>>when(_workspaceRepository.findById(anyLong())).thenReturn(dbWorkspace);
		Assertions.assertThat(_workspaceManager.findById(ID)).isEqualTo(workspace);
	}

	@Test 
	public void findWorkspaceById_IdIsNotNullAndIdDoesNotExist_ReturnNull() {

	    Mockito.<Optional<WorkspaceEntity>>when(_workspaceRepository.findById(anyLong())).thenReturn(Optional.empty());
		Assertions.assertThat(_workspaceManager.findById(ID)).isEqualTo(null);
	}
	
	@Test
	public void createWorkspace_WorkspaceIsNotNullAndWorkspaceDoesNotExist_StoreWorkspace() {

		WorkspaceEntity workspace =mock(WorkspaceEntity.class);
		Mockito.when(_workspaceRepository.save(any(WorkspaceEntity.class))).thenReturn(workspace);
		Assertions.assertThat(_workspaceManager.create(workspace)).isEqualTo(workspace);
	}

	@Test
	public void deleteWorkspace_WorkspaceExists_RemoveWorkspace() {

		WorkspaceEntity workspace =mock(WorkspaceEntity.class);
		_workspaceManager.delete(workspace);
		verify(_workspaceRepository).delete(workspace);
	}

	@Test
	public void updateWorkspace_WorkspaceIsNotNullAndWorkspaceExists_UpdateWorkspace() {
		
		WorkspaceEntity workspace =mock(WorkspaceEntity.class);
		Mockito.when(_workspaceRepository.save(any(WorkspaceEntity.class))).thenReturn(workspace);
		Assertions.assertThat(_workspaceManager.update(workspace)).isEqualTo(workspace);
		
	}

	@Test
	public void findAll_PageableIsNotNull_ReturnPage() {
		Page<WorkspaceEntity> workspace = mock(Page.class);
		Pageable pageable = mock(Pageable.class);
		Predicate predicate = mock(Predicate.class);

		Mockito.when(_workspaceRepository.findAll(any(Predicate.class),any(Pageable.class))).thenReturn(workspace);
		Assertions.assertThat(_workspaceManager.findAll(predicate,pageable)).isEqualTo(workspace);
	}
	
}
