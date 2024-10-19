package com.hmvss.api.persistence.repository.role;

import com.hmvss.api.persistence.model.Role;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface IRolePagSortRepository extends ListPagingAndSortingRepository<Role, Integer> {
}
