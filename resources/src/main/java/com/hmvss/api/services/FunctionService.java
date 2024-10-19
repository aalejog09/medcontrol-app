package com.hmvss.api.services;

import com.hmvss.api.dto.user.FunctionDTO;
import com.hmvss.api.persistence.mapper.FunctionMapper;
import com.hmvss.api.persistence.model.Function;
import com.hmvss.api.persistence.repository.Function.IFunctionRepository;
import com.hmvss.api.persistence.repository.RoleFunction.IRoleFunctionRepository;
import com.hmvss.api.services.interfaces.IFunctionService;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FunctionService implements IFunctionService {

    @Autowired
    private IFunctionRepository functionRepository;

    @Autowired
    private FunctionMapper functionMapper;

    @Override
    public Function getFunctionById(Long functionId){
        return functionRepository.findById(functionId)
                .orElseThrow(() -> new APIException(APIError.NOT_FOUND));
    }

    @Override
    public List<Function> getAllFuntcionList() {
        return functionRepository.findAll();
    }

    @Override
    public Function registerFunction(FunctionDTO functionDTO) {
        return functionRepository.save(functionMapper.toFunction(functionDTO));
    }

    @Override
    public Function enableFunction(Long functionId) {
        Function function = getFunctionById(functionId);
        function.setEnabled(true);
        return functionRepository.save(function);
    }

    @Override
    public Function disableFunction(Long functionId) {
        Function function = getFunctionById(functionId);
        function.setEnabled(false);
        return functionRepository.save(function);
    }
}
