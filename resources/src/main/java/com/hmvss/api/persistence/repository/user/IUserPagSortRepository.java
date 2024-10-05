package com.hmvss.api.persistence.repository.user;

import com.hmvss.api.persistence.model.User;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface IUserPagSortRepository extends ListPagingAndSortingRepository<User, Integer> {


}
