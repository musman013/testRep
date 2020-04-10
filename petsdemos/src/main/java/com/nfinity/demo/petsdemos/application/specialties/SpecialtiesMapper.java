package com.nfinity.demo.petsdemos.application.specialties;

import org.mapstruct.Mapper;
import com.nfinity.demo.petsdemos.application.specialties.dto.*;
import com.nfinity.demo.petsdemos.domain.model.SpecialtiesEntity;

@Mapper(componentModel = "spring")
public interface SpecialtiesMapper {

   SpecialtiesEntity createSpecialtiesInputToSpecialtiesEntity(CreateSpecialtiesInput specialtiesDto);
   
   CreateSpecialtiesOutput specialtiesEntityToCreateSpecialtiesOutput(SpecialtiesEntity entity);

   SpecialtiesEntity updateSpecialtiesInputToSpecialtiesEntity(UpdateSpecialtiesInput specialtiesDto);

   UpdateSpecialtiesOutput specialtiesEntityToUpdateSpecialtiesOutput(SpecialtiesEntity entity);

   FindSpecialtiesByIdOutput specialtiesEntityToFindSpecialtiesByIdOutput(SpecialtiesEntity entity);


}
