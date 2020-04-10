package com.nfinity.demo.petsdemos.domain.vetspecialties;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.nfinity.demo.petsdemos.domain.model.VetSpecialtiesEntity;
import com.nfinity.demo.petsdemos.domain.model.VetSpecialtiesId;
import com.nfinity.demo.petsdemos.domain.irepository.ISpecialtiesRepository;
import com.nfinity.demo.petsdemos.domain.model.SpecialtiesEntity;
import com.nfinity.demo.petsdemos.domain.irepository.IVetsRepository;
import com.nfinity.demo.petsdemos.domain.model.VetsEntity;
import com.nfinity.demo.petsdemos.domain.irepository.IVetSpecialtiesRepository;
import com.querydsl.core.types.Predicate;

@Repository
public class VetSpecialtiesManager implements IVetSpecialtiesManager {

    @Autowired
    IVetSpecialtiesRepository  _vetSpecialtiesRepository;
    
    @Autowired
	ISpecialtiesRepository  _specialtiesRepository;
    
    @Autowired
	IVetsRepository  _vetsRepository;
    
	public VetSpecialtiesEntity create(VetSpecialtiesEntity vetSpecialties) {

		return _vetSpecialtiesRepository.save(vetSpecialties);
	}

	public void delete(VetSpecialtiesEntity vetSpecialties) {

		_vetSpecialtiesRepository.delete(vetSpecialties);	
	}

	public VetSpecialtiesEntity update(VetSpecialtiesEntity vetSpecialties) {

		return _vetSpecialtiesRepository.save(vetSpecialties);
	}

	public VetSpecialtiesEntity findById(VetSpecialtiesId vetSpecialtiesId) {
    	Optional<VetSpecialtiesEntity> dbVetSpecialties= _vetSpecialtiesRepository.findById(vetSpecialtiesId);
		if(dbVetSpecialties.isPresent()) {
			VetSpecialtiesEntity existingVetSpecialties = dbVetSpecialties.get();
		    return existingVetSpecialties;
		} else {
		    return null;
		}

	}

	public Page<VetSpecialtiesEntity> findAll(Predicate predicate, Pageable pageable) {

		return _vetSpecialtiesRepository.findAll(predicate,pageable);
	}
  
   //Specialties
	public SpecialtiesEntity getSpecialties(VetSpecialtiesId vetSpecialtiesId) {
		
		Optional<VetSpecialtiesEntity> dbVetSpecialties= _vetSpecialtiesRepository.findById(vetSpecialtiesId);
		if(dbVetSpecialties.isPresent()) {
			VetSpecialtiesEntity existingVetSpecialties = dbVetSpecialties.get();
		    return existingVetSpecialties.getSpecialties();
		} else {
		    return null;
		}
	}
  
   //Vets
	public VetsEntity getVets(VetSpecialtiesId vetSpecialtiesId) {
		
		Optional<VetSpecialtiesEntity> dbVetSpecialties= _vetSpecialtiesRepository.findById(vetSpecialtiesId);
		if(dbVetSpecialties.isPresent()) {
			VetSpecialtiesEntity existingVetSpecialties = dbVetSpecialties.get();
		    return existingVetSpecialties.getVets();
		} else {
		    return null;
		}
	}
}
