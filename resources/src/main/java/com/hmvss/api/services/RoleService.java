package com.hmvss.api.services;

import com.hmvss.api.dto.user.RoleDTO;
import com.hmvss.api.persistence.mapper.RoleMapper;
import com.hmvss.api.persistence.model.Role;
import com.hmvss.api.persistence.repository.role.IRoleRepository;
import com.hmvss.api.services.interfaces.IRoleService;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(()-> new APIException(APIError.NOT_FOUND));
    }

    @Override
    public Role registerRole(RoleDTO roleDTO) {
        return roleRepository.save(roleMapper.toRole(roleDTO));
    }


}
