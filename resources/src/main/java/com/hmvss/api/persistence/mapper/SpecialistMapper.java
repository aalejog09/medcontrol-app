package com.hmvss.api.persistence.mapper;

import com.hmvss.api.dto.specialist.SpecialistDTO;
import com.hmvss.api.persistence.model.Specialist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {PersonalDataMapper.class, MedicalConsultationMapper.class})
public interface SpecialistMapper {
    SpecialistMapper INSTANCE = Mappers.getMapper(SpecialistMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "personalData", target = "personalData")
    @Mapping(source = "medicalCollegeCode", target = "medicalCollegeCode")
    @Mapping(source = "mppsCode", target = "mppsCode")
    @Mapping(source = "speciality", target = "speciality")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "medicalConsultations", target = "medicalConsultations")
    SpecialistDTO toSpecialistDTO(Specialist specialist);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "personalData", target = "personalData")
    @Mapping(source = "medicalCollegeCode", target = "medicalCollegeCode")
    @Mapping(source = "mppsCode", target = "mppsCode")
    @Mapping(source = "speciality", target = "speciality")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "medicalConsultations", target = "medicalConsultations")
    Specialist toSpecialist(SpecialistDTO specialistDTO);
}
