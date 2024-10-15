package com.hmvss.api.persistence.repository.location.city;

import com.hmvss.api.persistence.model.Location;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ICityPagSortRepository extends ListPagingAndSortingRepository<Location, Integer> {
}
