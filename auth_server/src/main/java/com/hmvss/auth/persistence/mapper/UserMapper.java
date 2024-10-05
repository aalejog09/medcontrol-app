package com.hmvss.auth.persistence.mapper;

import com.hmvss.auth.dto.user.UserDTO;
import com.hmvss.auth.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping( source = "username", target = "username"),
            @Mapping( source = "password", target = "password"),
            @Mapping( source = "enabled", target = "enabled")
    })
    UserDTO mapToUser(User user);

    List<UserDTO> mapUserDTOList(List<User> users);

}