package com.hmvss.api.services;

import com.hmvss.api.dto.specialist.SpecialistDTO;
import com.hmvss.api.persistence.mapper.SpecialistMapper;
import com.hmvss.api.persistence.model.PersonalData;
import com.hmvss.api.persistence.model.Specialist;
import com.hmvss.api.persistence.repository.specialist.ISpecialistRepository;
import com.hmvss.api.services.interfaces.IPersonalDataService;
import com.hmvss.api.services.interfaces.ISpecialistService;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SpecialistService implements ISpecialistService {

    @Autowired
    ISpecialistRepository specialistRepository;

    @Autowired
    SpecialistMapper specialistMapper;

    @Autowired
    private IPersonalDataService personalDataService;

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

    @Override
    public SpecialistDTO update(SpecialistDTO specialistDTO) {
        return null;
    }
}
