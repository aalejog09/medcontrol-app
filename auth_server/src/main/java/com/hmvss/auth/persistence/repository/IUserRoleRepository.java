package com.hmvss.auth.persistence.repository;

import com.hmvss.auth.persistence.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {

    List<UserRole> findByUserId(Long userId);

}
