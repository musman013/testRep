package com.nfinity.demo.petsdemos.application.vetspecialties;

import java.util.List;
import javax.validation.constraints.Positive;
import com.nfinity.demo.petsdemos.domain.model.VetSpecialtiesId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.nfinity.demo.petsdemos.commons.search.SearchCriteria;
import com.nfinity.demo.petsdemos.application.vetspecialties.dto.*;

@Service
public interface IVetSpecialtiesAppService {

	CreateVetSpecialtiesOutput create(CreateVetSpecialtiesInput vetspecialties);

    void delete(VetSpecialtiesId vetSpecialtiesId);

    UpdateVetSpecialtiesOutput update(VetSpecialtiesId vetSpecialtiesId, UpdateVetSpecialtiesInput input);

    FindVetSpecialtiesByIdOutput findById(VetSpecialtiesId vetSpecialtiesId);

    List<FindVetSpecialtiesByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;

	public VetSpecialtiesId parseVetSpecialtiesKey(String keysString);
    
    //Specialties
    GetSpecialtiesOutput getSpecialties(VetSpecialtiesId vetSpecialtiesId);
    
    //Vets
    GetVetsOutput getVets(VetSpecialtiesId vetSpecialtiesId);
}
