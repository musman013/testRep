package com.nfinity.demo.petsdemos.application.workspace;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nfinity.demo.petsdemos.domain.workspace.*;
import com.nfinity.demo.petsdemos.commons.search.*;
import com.nfinity.demo.petsdemos.application.workspace.dto.*;
import com.nfinity.demo.petsdemos.domain.model.QWorkspaceEntity;
import com.nfinity.demo.petsdemos.domain.model.WorkspaceEntity;
import com.nfinity.demo.petsdemos.commons.logging.LoggingHelper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@RunWith(SpringJUnit4ClassRunner.class)
public class WorkspaceAppServiceTest {

	@InjectMocks
	@Spy
	WorkspaceAppService _appService;

	@Mock
	private WorkspaceManager _workspaceManager;
	
	@Mock
	private WorkspaceMapper _mapper;

	@Mock
	private Logger loggerMock;

	@Mock
	private LoggingHelper logHelper;
	

    private static Long ID=15L;
    
	 
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(_appService);
		when(logHelper.getLogger()).thenReturn(loggerMock);
		doNothing().when(loggerMock).error(anyString());
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void findWorkspaceById_IdIsNotNullAndIdDoesNotExist_ReturnNull() {

		Mockito.when(_workspaceManager.findById(anyLong())).thenReturn(null);
		Assertions.assertThat(_appService.findById(ID)).isEqualTo(null);
	}
	
	@Test
	public void findWorkspaceById_IdIsNotNullAndIdExists_ReturnWorkspace() {

		WorkspaceEntity workspace = mock(WorkspaceEntity.class);
		Mockito.when(_workspaceManager.findById(anyLong())).thenReturn(workspace);
		Assertions.assertThat(_appService.findById(ID)).isEqualTo(_mapper.workspaceEntityToFindWorkspaceByIdOutput(workspace));
	}
	
	 @Test 
    public void createWorkspace_WorkspaceIsNotNullAndWorkspaceDoesNotExist_StoreWorkspace() { 
 
       WorkspaceEntity workspaceEntity = mock(WorkspaceEntity.class); 
       CreateWorkspaceInput workspace = new CreateWorkspaceInput();
   
		
        Mockito.when(_mapper.createWorkspaceInputToWorkspaceEntity(any(CreateWorkspaceInput.class))).thenReturn(workspaceEntity); 
        Mockito.when(_workspaceManager.create(any(WorkspaceEntity.class))).thenReturn(workspaceEntity);
      
        Assertions.assertThat(_appService.create(workspace)).isEqualTo(_mapper.workspaceEntityToCreateWorkspaceOutput(workspaceEntity)); 
    } 
	@Test
	public void updateWorkspace_WorkspaceIdIsNotNullAndIdExists_ReturnUpdatedWorkspace() {

		WorkspaceEntity workspaceEntity = mock(WorkspaceEntity.class);
		UpdateWorkspaceInput workspace= mock(UpdateWorkspaceInput.class);
		
		Mockito.when(_mapper.updateWorkspaceInputToWorkspaceEntity(any(UpdateWorkspaceInput.class))).thenReturn(workspaceEntity);
		Mockito.when(_workspaceManager.update(any(WorkspaceEntity.class))).thenReturn(workspaceEntity);
		Assertions.assertThat(_appService.update(ID,workspace)).isEqualTo(_mapper.workspaceEntityToUpdateWorkspaceOutput(workspaceEntity));
	}
    
	@Test
	public void deleteWorkspace_WorkspaceIsNotNullAndWorkspaceExists_WorkspaceRemoved() {

		WorkspaceEntity workspace= mock(WorkspaceEntity.class);
		Mockito.when(_workspaceManager.findById(anyLong())).thenReturn(workspace);
		
		_appService.delete(ID); 
		verify(_workspaceManager).delete(workspace);
	}
	
	@Test
	public void find_ListIsEmpty_ReturnList() throws Exception {

		List<WorkspaceEntity> list = new ArrayList<>();
		Page<WorkspaceEntity> foundPage = new PageImpl(list);
		Pageable pageable = mock(Pageable.class);
		List<FindWorkspaceByIdOutput> output = new ArrayList<>();
		SearchCriteria search= new SearchCriteria();
//		search.setType(1);
//		search.setValue("xyz");
//		search.setOperator("equals");

		Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
		Mockito.when(_workspaceManager.findAll(any(Predicate.class),any(Pageable.class))).thenReturn(foundPage);
		Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
	}
	
	@Test
	public void find_ListIsNotEmpty_ReturnList() throws Exception {

		List<WorkspaceEntity> list = new ArrayList<>();
		WorkspaceEntity workspace = mock(WorkspaceEntity.class);
		list.add(workspace);
    	Page<WorkspaceEntity> foundPage = new PageImpl(list);
		Pageable pageable = mock(Pageable.class);
		List<FindWorkspaceByIdOutput> output = new ArrayList<>();
        SearchCriteria search= new SearchCriteria();
//		search.setType(1);
//		search.setValue("xyz");
//		search.setOperator("equals");
		output.add(_mapper.workspaceEntityToFindWorkspaceByIdOutput(workspace));
		
		Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
    	Mockito.when(_workspaceManager.findAll(any(Predicate.class),any(Pageable.class))).thenReturn(foundPage);
		Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
	}
	
	@Test
	public void searchKeyValuePair_PropertyExists_ReturnBooleanBuilder() {
		QWorkspaceEntity workspace = QWorkspaceEntity.workspaceEntity;
	    SearchFields searchFields = new SearchFields();
		searchFields.setOperator("equals");
		searchFields.setSearchValue("xyz");
	    Map<String,SearchFields> map = new HashMap<>();
        map.put("name",searchFields);
		 Map<String,String> searchMap = new HashMap<>();
        searchMap.put("xyz",String.valueOf(ID));
		BooleanBuilder builder = new BooleanBuilder();
         builder.and(workspace.name.eq("xyz"));
		Assertions.assertThat(_appService.searchKeyValuePair(workspace,map,searchMap)).isEqualTo(builder);
	}
	
	@Test (expected = Exception.class)
	public void checkProperties_PropertyDoesNotExist_ThrowException() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("xyz");
		_appService.checkProperties(list);
	}
	
	@Test
	public void checkProperties_PropertyExists_ReturnNothing() throws Exception {
		List<String> list = new ArrayList<>();
        list.add("name");
		_appService.checkProperties(list);
	}
	
	@Test
	public void  search_SearchIsNotNullAndSearchContainsCaseThree_ReturnBooleanBuilder() throws Exception {
	
		Map<String,SearchFields> map = new HashMap<>();
		QWorkspaceEntity workspace = QWorkspaceEntity.workspaceEntity;
		List<SearchFields> fieldsList= new ArrayList<>();
		SearchFields fields=new SearchFields();
		SearchCriteria search= new SearchCriteria();
		search.setType(3);
		search.setValue("xyz");
		search.setOperator("equals");
        fields.setFieldName("name");
        fields.setOperator("equals");
		fields.setSearchValue("xyz");
        fieldsList.add(fields);
        search.setFields(fieldsList);
		BooleanBuilder builder = new BooleanBuilder();
        builder.or(workspace.name.eq("xyz"));
        Mockito.doNothing().when(_appService).checkProperties(any(List.class));
		Mockito.doReturn(builder).when(_appService).searchKeyValuePair(any(QWorkspaceEntity.class), any(HashMap.class), any(HashMap.class));
        
		Assertions.assertThat(_appService.search(search)).isEqualTo(builder);
	}
	
	@Test
	public void  search_StringIsNull_ReturnNull() throws Exception {

		Assertions.assertThat(_appService.search(null)).isEqualTo(null);
	}
	
}

