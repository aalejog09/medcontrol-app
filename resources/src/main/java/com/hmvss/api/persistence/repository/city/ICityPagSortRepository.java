package com.hmvss.api.persistence.repository.city;

import com.hmvss.api.persistence.model.City;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ICityPagSortRepository extends ListPagingAndSortingRepository<City, Integer> {
}
