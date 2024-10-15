package com.hmvss.api.persistence.repository.location;

import com.hmvss.api.persistence.model.Location;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ILocationPagSortRepository extends ListPagingAndSortingRepository<Location, Integer> {
}
