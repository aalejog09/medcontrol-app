package com.hmvss.api.persistence.repository.Function;

import com.hmvss.api.persistence.model.Function;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface IFunctionPagSortRepository extends ListPagingAndSortingRepository<Function, Integer> {
}
