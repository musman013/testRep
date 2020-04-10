package com.nfinity.demo.petsdemos.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

@Entity
@Table(name = "workspace", schema = "sample")
public class WorkspaceEntity implements Serializable {

	private Long id;
  	private String name;
 
  	public WorkspaceEntity() {
  	}

  	@Id
  	@Column(name = "id", nullable = false)
  	public Long getId() {
  		return id;
  	}

  	public void setId(Long id) {
  		this.id = id;
  	}
  
  @Basic
  @Column(name = "name", nullable = false, length =255)
  public String getName() {
  return name;
  }

  public void setName(String name) {
  this.name = name;
  }
  

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//      if (!(o instanceof WorkspaceEntity)) return false;
//        WorkspaceEntity workspace = (WorkspaceEntity) o;
//        return id != null && id.equals(workspace.id);
//  }

}

  
      


