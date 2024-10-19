package com.hmvss.api.services;

import com.hmvss.api.dto.user.RoleFunctionDTO;
import com.hmvss.api.persistence.mapper.RoleFunctionMapper;
import com.hmvss.api.persistence.model.Function;
import com.hmvss.api.persistence.model.Role;
import com.hmvss.api.persistence.model.RoleFunction;
import com.hmvss.api.persistence.repository.RoleFunction.IRoleFunctionRepository;
import com.hmvss.api.services.interfaces.IFunctionService;
import com.hmvss.api.services.interfaces.IRoleFunctionService;
import com.hmvss.api.services.interfaces.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RoleFunctionService implements IRoleFunctionService {

    @Autowired
    private IRoleFunctionRepository roleFunctionRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IFunctionService functionService;

    @Autowired
    private RoleFunctionMapper roleFunctionMapper;

    @Transactional
    @Override
    public RoleFunctionDTO assignFunctionToRole(Long roleId,  Long functionId) {
        // Buscar el rol y la función por sus IDs
        Role role = roleService.getRoleById(roleId);
        Function function = functionService.getFunctionById(functionId);

        // Crear y guardar RoleFunction
        RoleFunction roleFunction = new RoleFunction();
        roleFunction.setRole(role);
        roleFunction.setFunction(function);
        RoleFunction savedRoleFunction = roleFunctionRepository.save(roleFunction);

        // Convertir RoleFunction guardado a DTO y retornarlo
        return roleFunctionMapper.toRoleFunctionDTO(savedRoleFunction);
    }
}
