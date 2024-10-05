package com.hmvss.api.persistence.mapper;


import com.hmvss.api.dto.user.UserDTO;
import com.hmvss.api.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping( source = "username", target = "username"),
            @Mapping( source = "credentialExpired", target = "credentialExpired"),
            @Mapping( source = "locked", target = "locked"),
            @Mapping( source = "expired", target = "expired")
    })
    UserDTO mapUserDTO(User tokenResponse);

    List<UserDTO> mapUserDTOList(List<User> users);

}