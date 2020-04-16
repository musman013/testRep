package com.nfinity.demo.petsdemos.domain.authorization.userpermission;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.validation.constraints.Positive;
import com.nfinity.demo.petsdemos.domain.model.UserpermissionEntity;
import com.nfinity.demo.petsdemos.domain.model.UserpermissionId;
import com.nfinity.demo.petsdemos.domain.model.UserEntity;
import com.nfinity.demo.petsdemos.domain.model.PermissionEntity;

public interface IUserpermissionManager {

    // CRUD Operations
    UserpermissionEntity create(UserpermissionEntity userpermission);

    void delete(UserpermissionEntity userpermission);

    UserpermissionEntity update(UserpermissionEntity userpermission);

    UserpermissionEntity findById(UserpermissionId userpermissionId );

    Page<UserpermissionEntity> findAll(Predicate predicate, Pageable pageable);
   
    //User
    public UserEntity getUser(UserpermissionId userpermissionId );
  
    //Permission
    public PermissionEntity getPermission(UserpermissionId userpermissionId );
  
}
