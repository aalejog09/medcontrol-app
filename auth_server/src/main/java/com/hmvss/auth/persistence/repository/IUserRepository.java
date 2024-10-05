package com.hmvss.auth.persistence.repository;

import com.hmvss.auth.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    //User findByEmail(String email);

    User findByUsername(String usermane);
}
