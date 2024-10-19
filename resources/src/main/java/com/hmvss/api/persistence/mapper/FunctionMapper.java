package com.hmvss.api.persistence.mapper;

import com.hmvss.api.dto.user.FunctionDTO;
import com.hmvss.api.persistence.model.Function;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FunctionMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "description", target = "description"),
        @Mapping(source = "creationDate", target = "creationDate"),
        @Mapping(source = "enabled", target = "enabled")
    })
    FunctionDTO toFunctionDTO(Function function);

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "description", target = "description"),
        @Mapping(source = "creationDate", target = "creationDate"),
        @Mapping(source = "enabled", target = "enabled")
    })
    Function toFunction(FunctionDTO functionDTO);

    List<FunctionDTO> toFunctionDTOList(List<Function> functions);

    List<Function> toFunctionList(List<FunctionDTO> functionDTOs);
}
