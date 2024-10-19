package com.hmvss.api.services.interfaces;

import com.hmvss.api.persistence.model.Function;

public interface IFunctionService {

    Function getFunctionById(Long functionId);
}
