package com.hmvss.api.services;

import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.dto.specialist.SpecialistDTO;
import com.hmvss.api.persistence.mapper.SpecialistMapper;
import com.hmvss.api.persistence.model.PersonalData;
import com.hmvss.api.persistence.model.Specialist;
import com.hmvss.api.persistence.repository.specialist.ISpecialistRepository;
import com.hmvss.api.services.interfaces.IPersonalDataService;
import com.hmvss.api.services.interfaces.ISpecialistService;
import com.hmvss.api.util.Utility;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
public class SpecialistService implements ISpecialistService {

    @Autowired
    ISpecialistRepository specialistRepository;

    @Autowired
    SpecialistMapper specialistMapper;

    @Autowired
    private IPersonalDataService personalDataService;

    @Autowired
    Utility utility;

    @Override
    @Transactional
    public SpecialistDTO register(SpecialistDTO specialistDTO) {
        PersonalData personalData = personalDataService.register(specialistDTO.getPersonalData());
        if(personalData==null){
            throw new APIException(APIError.DB_SAVING_ERROR);
        }
        Specialist specialist = specialistMapper.toSpecialist(specialistDTO);
        specialist.setPersonalData(personalData);
        specialist = specialistRepository.save(specialist);
        return specialistMapper.toSpecialistDTO(specialist);
    }

    @Transactional
    @Override
    public SpecialistDTO update(SpecialistDTO specialistDTO) {
        log.info("Upadate specialistDTO:{}",specialistDTO);
        Specialist specialist = specialistRepository.findById(specialistDTO.getId()).orElseThrow(()-> new APIException(APIError.NOT_FOUND));

        PersonalDataDTO  personalDataDTO = personalDataService.update(specialistDTO.getPersonalData());
        PersonalData personalData = personalDataService.mapToPersonalData(personalDataDTO);
        specialist.setPersonalData(personalData);
        // Update fields only if they are not blank and different from current values
        utility.updateIfNotBlankAndDifferent(specialist::setType, specialist.getType(), specialistDTO.getType());
        utility.updateIfNotBlankAndDifferent(specialist::setMppsCode, specialist.getMppsCode(), specialistDTO.getMppsCode());
        utility.updateIfNotBlankAndDifferent(specialist::setSpeciality, specialist.getSpeciality(), specialistDTO.getSpeciality());
        utility.updateIfNotBlankAndDifferent(specialist::setMedicalCollegeCode, specialist.getMedicalCollegeCode(), specialistDTO.getMedicalCollegeCode());
        specialist = specialistRepository.save(specialist);
        return specialistMapper.toSpecialistDTO(specialist);
    }

    @Override
    public SpecialistDTO getByMedicalCollegeCode(String medicalCollegeCode) {
        return specialistMapper.toSpecialistDTO(specialistRepository.findByMedicalCollegeCode(medicalCollegeCode)
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND)));
    }

    @Override
    public SpecialistDTO getByMppsCode(String mppsCode) {
        return specialistMapper.toSpecialistDTO(specialistRepository.findByMppsCode(mppsCode)
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND)));
    }


}
