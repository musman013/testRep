package com.nfinity.demo.petsdemos.application.authorization.userrole;

import java.util.List;
import javax.validation.constraints.Positive;
import com.nfinity.demo.petsdemos.domain.model.UserroleId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.nfinity.demo.petsdemos.commons.search.SearchCriteria;
import com.nfinity.demo.petsdemos.application.authorization.userrole.dto.*;

@Service
public interface IUserroleAppService {

	CreateUserroleOutput create(CreateUserroleInput userrole);

    void delete(UserroleId userroleId);

    UpdateUserroleOutput update(UserroleId userroleId, UpdateUserroleInput input);

    FindUserroleByIdOutput findById(UserroleId userroleId);

    List<FindUserroleByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;

	public UserroleId parseUserroleKey(String keysString);
    
    //User
    GetUserOutput getUser(UserroleId userroleId);
    
    //Role
    GetRoleOutput getRole(UserroleId userroleId);
}
