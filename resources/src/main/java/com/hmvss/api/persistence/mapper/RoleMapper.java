package com.hmvss.api.persistence.mapper;

import com.hmvss.api.dto.user.FunctionDTO;
import com.hmvss.api.dto.user.RoleDTO;
import com.hmvss.api.persistence.model.Role;
import com.hmvss.api.persistence.model.RoleFunction;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",uses = {FunctionMapper.class})
public interface RoleMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "roleName", target = "roleName"),
        @Mapping(source = "creationDate", target = "creationDate"),
        @Mapping(source = "enabled", target = "enabled")
    })
    RoleDTO toRoleDTO(Role role);

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "roleName", target = "roleName"),
        @Mapping(source = "creationDate", target = "creationDate"),
        @Mapping(source = "enabled", target = "enabled")
    })
    Role toRole(RoleDTO roleDTO);

    List<RoleDTO> toDTOList(List<Role> roles);

    List<Role> toEntityList(List<RoleDTO> rolesDTOs);

}
