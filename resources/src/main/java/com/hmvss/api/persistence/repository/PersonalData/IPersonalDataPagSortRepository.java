package com.hmvss.api.persistence.repository.PersonalData;

import com.hmvss.api.persistence.model.PersonalData;
import com.hmvss.api.services.PersonalDataService;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface IPersonalDataPagSortRepository extends ListPagingAndSortingRepository<PersonalData, Integer> {


}
