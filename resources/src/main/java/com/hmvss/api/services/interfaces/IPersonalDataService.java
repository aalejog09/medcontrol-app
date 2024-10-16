package com.hmvss.api.services.interfaces;


import com.hmvss.api.dto.pagination.PaginationDTO;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;

import java.time.LocalDate;


public interface IPersonalDataService {

        PersonalDataDTO register(PersonalDataDTO personalDataDTO);

        PersonalDataDTO getPersonalDataByDniAndBornDate(String dni, LocalDate bornDate);

        PersonalDataDTO update(PersonalDataDTO personalDataDTO);

        PaginationDTO getAllPersonalDataListPageables(int page, int elements);
}
