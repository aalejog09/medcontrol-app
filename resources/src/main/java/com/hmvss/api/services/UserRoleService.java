package com.hmvss.api.services;

import com.hmvss.api.persistence.model.Role;
import com.hmvss.api.persistence.model.UserRole;
import com.hmvss.api.persistence.repository.userRole.IUserRoleRepository;
import com.hmvss.api.services.interfaces.IUserRoleService;
import com.hmvss.api.persistence.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserRoleService implements IUserRoleService {

     @Autowired
    IUserRoleRepository userRoleRepository;

    @Override
    public UserRole assingRoleToUser(User user, Role role) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        return userRoleRepository.save(userRole);
    }
}
