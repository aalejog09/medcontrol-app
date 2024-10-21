package com.hmvss.api.services.interfaces;

import com.hmvss.api.dto.user.FunctionDTO;
import com.hmvss.api.persistence.model.Function;

import java.util.List;

public interface IFunctionService {

    Function getFunctionById(Long functionId);

    List<FunctionDTO> getAllFuntcionList();

    Function registerFunction (FunctionDTO functionDTO);

    FunctionDTO changeFunctionStatus(Long functionId, boolean status);


}
