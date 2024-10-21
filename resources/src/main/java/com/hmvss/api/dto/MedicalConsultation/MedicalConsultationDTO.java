package com.hmvss.api.dto.MedicalConsultation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hmvss.api.dto.specialist.SpecialistDTO;
import lombok.Data;

import java.util.Date;

@Data
public class MedicalConsultationDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("diagnosis")
    private String diagnosis;

    @JsonProperty("treatment")
    private String treatment;

    @JsonProperty("registryDate")
    private Date registryDate;

    @JsonProperty("firstConsult")
    private boolean firstConsult;

    @JsonProperty("specialist")
    private SpecialistDTO specialist;
    //private PatientDTO patient;
    //private VitalSignalsDTO vitalSignals;

}
