package com.hmvss.api.services.interfaces;

import com.hmvss.api.dto.user.RoleFunctionDTO;

public interface IRoleFunctionService {

    RoleFunctionDTO  assignFunctionToRole(Long roleId,  Long functionId);
}
