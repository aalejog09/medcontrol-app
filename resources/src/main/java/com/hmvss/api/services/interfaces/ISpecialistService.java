package com.hmvss.api.services.interfaces;

import com.hmvss.api.dto.specialist.SpecialistDTO;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;

public interface  ISpecialistService {

    SpecialistDTO register(SpecialistDTO specialistDTO);

    SpecialistDTO update(SpecialistDTO specialistDTO);

    SpecialistDTO getByMedicalCollegeCode(String medicalCollegeCode);

    SpecialistDTO getByMppsCode(String mppsCode);

}
