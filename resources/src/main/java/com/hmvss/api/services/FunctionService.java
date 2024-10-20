package com.hmvss.api.services;

import com.hmvss.api.dto.user.AddFunctionDTO;
import com.hmvss.api.dto.user.FunctionDTO;
import com.hmvss.api.persistence.mapper.FunctionMapper;
import com.hmvss.api.persistence.model.Function;
import com.hmvss.api.persistence.model.Role;
import com.hmvss.api.persistence.repository.Function.IFunctionRepository;
import com.hmvss.api.services.interfaces.IFunctionService;
import com.hmvss.api.util.exceptions.APIError;
import com.hmvss.api.util.exceptions.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<FunctionDTO> getAllFuntcionList() {
        return functionMapper.toFunctionDTOList(functionRepository.findAll());
    }

    @Override
    public Function registerFunction(FunctionDTO functionDTO) {
        Function function = functionMapper.toFunction(functionDTO);
        function.setCreationDate(new Date());
        function.setEnabled(true);
        return functionRepository.save(function);
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
