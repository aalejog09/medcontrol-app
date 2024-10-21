package com.hmvss.api.services.interfaces;

import com.hmvss.api.dto.specialist.SpecialistDTO;

public interface  ISpecialistService {

    SpecialistDTO register(SpecialistDTO specialistDTO);

    SpecialistDTO update(SpecialistDTO specialistDTO);

}
