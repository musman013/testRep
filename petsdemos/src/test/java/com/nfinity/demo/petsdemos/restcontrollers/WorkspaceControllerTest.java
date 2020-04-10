package com.nfinity.demo.petsdemos.restcontrollers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nfinity.demo.petsdemos.commons.logging.LoggingHelper;
import com.nfinity.demo.petsdemos.application.workspace.WorkspaceAppService;
import com.nfinity.demo.petsdemos.application.workspace.dto.*;
import com.nfinity.demo.petsdemos.domain.irepository.IWorkspaceRepository;
import com.nfinity.demo.petsdemos.domain.model.WorkspaceEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
				properties = "spring.profiles.active=test")
public class WorkspaceControllerTest {
	@Autowired
	private SortHandlerMethodArgumentResolver sortArgumentResolver;

	@Autowired 
	private IWorkspaceRepository workspace_repository;
	
	@SpyBean
	private WorkspaceAppService workspaceAppService;

	@SpyBean
	private LoggingHelper logHelper;

	@Mock
	private Logger loggerMock;

	private WorkspaceEntity workspace;

	private MockMvc mvc;
	
	@Autowired
	EntityManagerFactory emf;
	
    static EntityManagerFactory emfs;
	
	@PostConstruct
	public void init() {
	this.emfs = emf;
	}

	@AfterClass
	public static void cleanup() {
		EntityManager em = emfs.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("drop table sample.workspace CASCADE").executeUpdate();
		em.getTransaction().commit();
	}

	@Autowired 
	private CacheManager cacheManager; 
	
	public void evictAllCaches(){ 
		for(String name : cacheManager.getCacheNames()) {
			cacheManager.getCache(name).clear(); 
		} 
	}

	public WorkspaceEntity createEntity() {
	
		WorkspaceEntity workspace = new WorkspaceEntity();
		workspace.setId(1L);
  		workspace.setName("1");
		
		return workspace;
	}

	public CreateWorkspaceInput createWorkspaceInput() {
	
	    CreateWorkspaceInput workspace = new CreateWorkspaceInput();
		workspace.setId(2L);
  		workspace.setName("2");
	    
		
		return workspace;
	}

	public WorkspaceEntity createNewEntity() {
		WorkspaceEntity workspace = new WorkspaceEntity();
		workspace.setId(3L);
  		workspace.setName("3");
		return workspace;
	}
	

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		evictAllCaches();
		final WorkspaceController workspaceController = new WorkspaceController(workspaceAppService,
	logHelper);
		when(logHelper.getLogger()).thenReturn(loggerMock);
		doNothing().when(loggerMock).error(anyString());

		this.mvc = MockMvcBuilders.standaloneSetup(workspaceController)
				.setCustomArgumentResolvers(sortArgumentResolver)
				.setControllerAdvice()
				.build();
	}

	@Before
	public void initTest() {

		workspace= createEntity();
		List<WorkspaceEntity> list= workspace_repository.findAll();
		if(!list.contains(workspace)) {
			workspace=workspace_repository.save(workspace);
		}

	}

	@Test
	public void FindById_IdIsValid_ReturnStatusOk() throws Exception {
	
		mvc.perform(get("/workspace/" + workspace.getId())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}  

	@Test
	public void FindById_IdIsNotValid_ReturnStatusNotFound() throws Exception {

		mvc.perform(get("/workspace/111")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());

	}    
	@Test
	public void CreateWorkspace_WorkspaceDoesNotExist_ReturnStatusOk() throws Exception {
		CreateWorkspaceInput workspace = createWorkspaceInput();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(workspace);

		mvc.perform(post("/workspace").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk());

	}     

	@Test
	public void DeleteWorkspace_IdIsNotValid_ThrowEntityNotFoundException() throws Exception {

        doReturn(null).when(workspaceAppService).findById(111L);
        org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(delete("/workspace/111")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())).hasCause(new EntityNotFoundException("There does not exist a workspace with a id=111"));

	}  

	@Test
	public void Delete_IdIsValid_ReturnStatusNoContent() throws Exception {
	
	 WorkspaceEntity entity =  createNewEntity();
		
		entity = workspace_repository.save(entity);

		FindWorkspaceByIdOutput output= new FindWorkspaceByIdOutput();
  		output.setId(entity.getId());
  		output.setName(entity.getName());
        Mockito.when(workspaceAppService.findById(entity.getId())).thenReturn(output);
        
		mvc.perform(delete("/workspace/" + entity.getId())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}  


	@Test
	public void UpdateWorkspace_WorkspaceDoesNotExist_ReturnStatusNotFound() throws Exception {

        doReturn(null).when(workspaceAppService).findById(111L);

		UpdateWorkspaceInput workspace = new UpdateWorkspaceInput();
		workspace.setId(111L);
  		workspace.setName("111");

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(workspace);
		mvc.perform(put("/workspace/111").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isNotFound());

	}    

	@Test
	public void UpdateWorkspace_WorkspaceExists_ReturnStatusOk() throws Exception {
		WorkspaceEntity entity =  createNewEntity();
		entity = workspace_repository.save(entity);
		FindWorkspaceByIdOutput output= new FindWorkspaceByIdOutput();
  		output.setId(entity.getId());
  		output.setName(entity.getName());
        Mockito.when(workspaceAppService.findById(entity.getId())).thenReturn(output);
        
		UpdateWorkspaceInput workspace = new UpdateWorkspaceInput();
  		workspace.setId(entity.getId());
  		workspace.setName(entity.getName());
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(workspace);
	
		mvc.perform(put("/workspace/" + entity.getId()).contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk());

		WorkspaceEntity de = createEntity();
		de.setId(entity.getId());
		workspace_repository.delete(de);

	}    
	@Test
	public void FindAll_SearchIsNotNullAndPropertyIsValid_ReturnStatusOk() throws Exception {

		mvc.perform(get("/workspace?search=id[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}    

	@Test
	public void FindAll_SearchIsNotNullAndPropertyIsNotValid_ThrowException() throws Exception {

		org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(get("/workspace?search=workspaceid[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())).hasCause(new Exception("Wrong URL Format: Property workspaceid not found!"));

	} 
	
    

}
