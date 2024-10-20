package com.hmvss.api.persistence.repository.role;

import com.hmvss.api.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String roleName);

    @Query("SELECT r FROM Role r LEFT JOIN FETCH r.functions WHERE r.id = :roleId")
    Optional<Role> findByIdWithFunctions(@Param("roleId") Long roleId);

}
