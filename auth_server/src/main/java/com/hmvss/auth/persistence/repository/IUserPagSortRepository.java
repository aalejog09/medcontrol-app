package com.hmvss.auth.persistence.repository;


import com.hmvss.auth.persistence.model.User;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface IUserPagSortRepository extends ListPagingAndSortingRepository<User, Integer> {


}
