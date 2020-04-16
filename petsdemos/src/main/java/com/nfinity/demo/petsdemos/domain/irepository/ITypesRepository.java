package com.nfinity.demo.petsdemos.domain.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.nfinity.demo.petsdemos.domain.model.PetsEntity; 
import java.util.List;
import com.nfinity.demo.petsdemos.domain.model.TypesEntity;
import org.javers.spring.annotation.JaversSpringDataAuditable;

@JaversSpringDataAuditable
@RepositoryRestResource(collectionResourceRel = "types", path = "types")
public interface ITypesRepository extends JpaRepository<TypesEntity, Integer>,QuerydslPredicateExecutor<TypesEntity> {

}
