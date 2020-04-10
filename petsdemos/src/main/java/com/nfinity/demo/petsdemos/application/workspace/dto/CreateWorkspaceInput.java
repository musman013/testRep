package com.nfinity.demo.petsdemos.application.workspace.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class CreateWorkspaceInput {

  @NotNull(message = "id Should not be null")
  private Long id;
  
  @NotNull(message = "name Should not be null")
  @Length(max = 255, message = "name must be less than 255 characters")
  private String name;
  

  public Long getId() {
  return id;
  }

  public void setId(Long id){
  this.id = id;
  }
  
  public String getName() {
  return name;
  }

  public void setName(String name){
  this.name = name;
  }
  
 
}
