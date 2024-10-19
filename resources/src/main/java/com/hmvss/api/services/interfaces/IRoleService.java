package com.hmvss.api.services.interfaces;

import com.hmvss.api.dto.user.RoleDTO;
import com.hmvss.api.persistence.model.Role;

public interface IRoleService {

     Role getRoleById(Long id);

     Role registerRole(RoleDTO roleDTO);
}
