package com.hmvss.api.dto.specialist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hmvss.api.dto.MedicalConsultation.MedicalConsultationDTO;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("medicalCollegeCode")
    private String medicalCollegeCode;

    @JsonProperty("mppsCode")
    private String mppsCode;

    @JsonProperty("speciality")
    private String speciality;

    @JsonProperty("type")
    private String type;

    @Valid
    @JsonProperty("personalData")
    private PersonalDataDTO personalData;

/*
    @JsonProperty("medicalConsultations")
    List<MedicalConsultationDTO> medicalConsultations;
*/

}