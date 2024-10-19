package com.hmvss.api.services.interfaces;

import com.hmvss.api.dto.user.FunctionDTO;
import com.hmvss.api.persistence.model.Function;

import java.util.List;

public interface IFunctionService {

    Function getFunctionById(Long functionId);

    List<Function> getAllFuntcionList();

    Function registerFunction (FunctionDTO functionDTO);

    Function enableFunction(Long functionId);
    Function disableFunction(Long functionId);

}
