package com.hmvss.api.persistence.mapper;

import com.hmvss.api.dto.user.RoleFunctionDTO;
import com.hmvss.api.persistence.model.RoleFunction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleFunctionMapper {

    @Mappings({
        @Mapping(source = "roleFunctionId", target = "roleFunctionId"),
        @Mapping(source = "role.id", target = "roleId"),
        @Mapping(source = "function.id", target = "functionId")
    })
    RoleFunctionDTO toRoleFunctionDTO(RoleFunction roleFunction);

    @Mappings({
        @Mapping(source = "roleFunctionId", target = "roleFunctionId"),
        @Mapping(source = "roleId", target = "role.id"),
        @Mapping(source = "functionId", target = "function.id")
    })
    RoleFunction toRoleFunction(RoleFunctionDTO roleFunctionDTO);

    List<RoleFunctionDTO> toRoleFunctionDTOList(List<RoleFunction> roleFunctions);

    List<RoleFunction> toRoleFunctionList(List<RoleFunctionDTO> roleFunctionDTOs);
}
