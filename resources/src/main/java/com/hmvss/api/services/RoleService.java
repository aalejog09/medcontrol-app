package com.hmvss.api.services;

import com.hmvss.api.dto.user.AddFunctionDTO;
import com.hmvss.api.dto.user.FunctionDTO;
import com.hmvss.api.dto.user.RoleDTO;
import com.hmvss.api.persistence.mapper.FunctionMapper;
import com.hmvss.api.persistence.mapper.RoleMapper;
import com.hmvss.api.persistence.model.Function;
import com.hmvss.api.persistence.model.Role;
import com.hmvss.api.persistence.repository.role.IRoleRepository;
import com.hmvss.api.services.interfaces.IRoleService;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private FunctionService functionService;

    @Autowired
    private FunctionMapper functionMapper;

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(()-> new APIException(APIError.NOT_FOUND));
    }

    @Override
    public Role registerRole(RoleDTO roleDTO) {
        Role role = roleMapper.toRole(roleDTO);
        role.setRoleName(roleDTO.getRoleName().toUpperCase());
        role.setEnabled(true);
        role.setCreationDate(new Date());
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName.toUpperCase()).orElseThrow(()-> new APIException(APIError.NOT_FOUND));
    }

    @Override
    @Transactional
    public RoleDTO assignFunctionToRole(AddFunctionDTO addFunctionDTO) {
        Role role = roleRepository.findById(addFunctionDTO.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
        Function function = functionService.getFunctionById(addFunctionDTO.getFunctionId());

        role.getFunctions().add(function);
        role = roleRepository.save(role);
        return roleMapper.toRoleDTO(role);
    }

    @Override
    @Transactional
    public RoleDTO removeFunctionFromRole(AddFunctionDTO removeFunctionDTO) {
        Role role = roleRepository.findById(removeFunctionDTO.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
        Function function = functionService.getFunctionById(removeFunctionDTO.getFunctionId());

        if (role.getFunctions().contains(function)) {
            role.getFunctions().remove(function);
        } else {
            throw new APIException(APIError.NOT_ASSOCIATED_FUNCTION);
        }
        role = roleRepository.save(role);
        return roleMapper.toRoleDTO(role);
    }

    @Override
    public List<FunctionDTO> getFunctionsByRolename(String roleName) {
        Role role = getRoleByName(roleName);
        Set<Function> funtions = role.getFunctions();
        List<Function> functionList = new java.util.ArrayList<>(List.of());
        functionList.addAll(funtions);
        return functionMapper.toFunctionDTOList(functionList);
    }

}
