package com.nfinity.demo.petsdemos.domain.vetspecialties;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.validation.constraints.Positive;
import com.nfinity.demo.petsdemos.domain.model.VetSpecialtiesEntity;
import com.nfinity.demo.petsdemos.domain.model.VetSpecialtiesId;
import com.nfinity.demo.petsdemos.domain.model.SpecialtiesEntity;
import com.nfinity.demo.petsdemos.domain.model.VetsEntity;

public interface IVetSpecialtiesManager {
    // CRUD Operations
    VetSpecialtiesEntity create(VetSpecialtiesEntity vetSpecialties);

    void delete(VetSpecialtiesEntity vetSpecialties);

    VetSpecialtiesEntity update(VetSpecialtiesEntity vetSpecialties);

    VetSpecialtiesEntity findById(VetSpecialtiesId vetSpecialtiesId);
	
    Page<VetSpecialtiesEntity> findAll(Predicate predicate, Pageable pageable);
   
    //Specialties
    public SpecialtiesEntity getSpecialties(VetSpecialtiesId vetSpecialtiesId);
   
    //Vets
    public VetsEntity getVets(VetSpecialtiesId vetSpecialtiesId);
}
