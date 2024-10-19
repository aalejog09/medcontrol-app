package com.hmvss.api.services.interfaces;


import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.persistence.model.PersonalData;

import java.time.LocalDate;


public interface IPersonalDataService {

        PersonalData register(PersonalDataDTO personalDataDTO);

        PersonalDataDTO getPersonalDataByDniAndBornDate(String dni, LocalDate bornDate);

        PersonalDataDTO update(PersonalDataDTO personalDataDTO);

        PaginationDTO getAllPersonalDataListPageables(int page, int elements);
}
