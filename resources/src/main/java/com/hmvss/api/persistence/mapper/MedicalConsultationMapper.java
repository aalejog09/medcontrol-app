package com.hmvss.api.persistence.mapper;

import com.hmvss.api.dto.MedicalConsultation.MedicalConsultationDTO;
import com.hmvss.api.dto.user.UserDTO;
import com.hmvss.api.persistence.model.MedicalConsultation;
import com.hmvss.api.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalConsultationMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "reason", target = "reason")
    @Mapping(source = "diagnosis", target = "diagnosis")
    @Mapping(source = "treatment", target = "treatment")
    @Mapping(source = "registryDate", target = "registryDate")
    @Mapping(source = "firstConsult", target = "firstConsult")
        //@Mapping(source = "specialist", target = "specialist")
        //@Mapping(source = "patient", target = "patient")
        // @Mapping(source = "vitalSignals", target = "vitalSignals")
    MedicalConsultationDTO toMedicalConsultationDTO(MedicalConsultation medicalConsultation);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "reason", target = "reason")
    @Mapping(source = "diagnosis", target = "diagnosis")
    @Mapping(source = "treatment", target = "treatment")
    @Mapping(source = "registryDate", target = "registryDate")
    @Mapping(source = "firstConsult", target = "firstConsult")
        //@Mapping(source = "specialist", target = "specialist")
        //@Mapping(source = "patient", target = "patient")
        // @Mapping(source = "vitalSignals", target = "vitalSignals")
    MedicalConsultation toMedicalConsultation(MedicalConsultationDTO medicalConsultationDTO);
    List<MedicalConsultationDTO> toMedicalConsultationDTOList(List<MedicalConsultation> medicalConsultationList);
    List<MedicalConsultation> toMedicalConsultationList(List<MedicalConsultationDTO> medicalConsultationDTOList);
}
