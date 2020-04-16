package com.nfinity.demo.petsdemos.domain.vets;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.nfinity.demo.petsdemos.domain.model.VetsEntity;
import com.nfinity.demo.petsdemos.domain.irepository.IVetSpecialtiesRepository;
import com.nfinity.demo.petsdemos.domain.irepository.IVetsRepository;
import com.querydsl.core.types.Predicate;

@Repository
public class VetsManager implements IVetsManager {

    @Autowired
    IVetsRepository  _vetsRepository;
    
    @Autowired
	IVetSpecialtiesRepository  _vetspecialtiesRepository;
    
	public VetsEntity create(VetsEntity vets) {

		return _vetsRepository.save(vets);
	}

	public void delete(VetsEntity vets) {

		_vetsRepository.delete(vets);	
	}

	public VetsEntity update(VetsEntity vets) {

		return _vetsRepository.save(vets);
	}

	public VetsEntity findById(Integer vetsId) {
    	Optional<VetsEntity> dbVets= _vetsRepository.findById(vetsId);
		if(dbVets.isPresent()) {
			VetsEntity existingVets = dbVets.get();
		    return existingVets;
		} else {
		    return null;
		}

	}

	public Page<VetsEntity> findAll(Predicate predicate, Pageable pageable) {

		return _vetsRepository.findAll(predicate,pageable);
	}
}
