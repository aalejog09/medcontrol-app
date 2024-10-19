package com.hmvss.api.services;

import com.hmvss.api.persistence.model.Function;
import com.hmvss.api.persistence.repository.Function.IFunctionRepository;
import com.hmvss.api.persistence.repository.RoleFunction.IRoleFunctionRepository;
import com.hmvss.api.services.interfaces.IFunctionService;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FunctionService implements IFunctionService {

    @Autowired
    private IFunctionRepository functionRepository;

    @Override
    public Function getFunctionById(Long functionId){
        return functionRepository.findById(functionId)
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND));
    }
}
