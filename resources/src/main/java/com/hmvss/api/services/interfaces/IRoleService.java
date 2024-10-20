package com.hmvss.api.services.interfaces;

import com.hmvss.api.dto.user.AddFunctionDTO;
import com.hmvss.api.dto.user.RoleDTO;
import com.hmvss.api.persistence.model.Role;

public interface IRoleService {

     Role getRoleById(Long id);

     Role registerRole(RoleDTO roleDTO);

     Role getRoleByName(String roleName);

     RoleDTO assignFunctionToRole(AddFunctionDTO addFunctionDTO);

     RoleDTO removeFunctionFromRole(AddFunctionDTO addFunctionDTO);

}
