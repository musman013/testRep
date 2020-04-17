package com.nfinity.demo.petsdemos.domain.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.nfinity.demo.petsdemos.domain.model.VisitsEntity; 
import java.util.List;
import com.nfinity.demo.petsdemos.domain.model.PetsEntity;
import org.javers.spring.annotation.JaversSpringDataAuditable;

@JaversSpringDataAuditable
@RepositoryRestResource(collectionResourceRel = "pets", path = "pets")
public interface IPetsRepository extends JpaRepository<PetsEntity, Integer>,QuerydslPredicateExecutor<PetsEntity> {

}
