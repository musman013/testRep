package com.nfinity.demo.petsdemos.application.pets;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.nfinity.demo.petsdemos.domain.model.TypesEntity;
import com.nfinity.demo.petsdemos.domain.model.OwnersEntity;
import com.nfinity.demo.petsdemos.application.pets.dto.*;
import com.nfinity.demo.petsdemos.domain.model.PetsEntity;

@Mapper(componentModel = "spring")
public interface PetsMapper {

   PetsEntity createPetsInputToPetsEntity(CreatePetsInput petsDto);
   
   @Mappings({ 
   @Mapping(source = "types.id", target = "typeId"),                   
   @Mapping(source = "types.name", target = "typesDescriptiveField"),                    
   @Mapping(source = "owners.id", target = "ownerId"),                   
   @Mapping(source = "owners.city", target = "ownersDescriptiveField"),                    
   }) 
   CreatePetsOutput petsEntityToCreatePetsOutput(PetsEntity entity);

   PetsEntity updatePetsInputToPetsEntity(UpdatePetsInput petsDto);

   @Mappings({ 
   @Mapping(source = "types.id", target = "typeId"),                   
   @Mapping(source = "types.name", target = "typesDescriptiveField"),                    
   @Mapping(source = "owners.id", target = "ownerId"),                   
   @Mapping(source = "owners.city", target = "ownersDescriptiveField"),                    
   }) 
   UpdatePetsOutput petsEntityToUpdatePetsOutput(PetsEntity entity);

   @Mappings({ 
   @Mapping(source = "types.id", target = "typeId"),                   
   @Mapping(source = "types.name", target = "typesDescriptiveField"),                    
   @Mapping(source = "owners.id", target = "ownerId"),                   
   @Mapping(source = "owners.city", target = "ownersDescriptiveField"),                    
   }) 
   FindPetsByIdOutput petsEntityToFindPetsByIdOutput(PetsEntity entity);


   @Mappings({
   @Mapping(source = "types.id", target = "id"),                  
   @Mapping(source = "types.name", target = "name"),                  
   @Mapping(source = "pets.id", target = "petsId"),
   })
   GetTypesOutput typesEntityToGetTypesOutput(TypesEntity types, PetsEntity pets);

   @Mappings({
   @Mapping(source = "owners.id", target = "id"),                  
   @Mapping(source = "pets.id", target = "petsId"),
   })
   GetOwnersOutput ownersEntityToGetOwnersOutput(OwnersEntity owners, PetsEntity pets);

}
