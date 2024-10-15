package com.hmvss.api.persistence.mapper;

import com.hmvss.api.dto.user.UserDTO;
import com.hmvss.api.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = PersonalDataMapper.class)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "enabled", target = "enabled"),
            @Mapping(source = "locked", target = "locked"),
            @Mapping(source = "expired", target = "expired"),
            @Mapping(source = "credentialExpired", target = "credentialExpired"),
            @Mapping(source = "personalData", target = "personalData")
    })
    UserDTO toUserDTO(User user);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "enabled", target = "enabled"),
            @Mapping(source = "locked", target = "locked"),
            @Mapping(source = "expired", target = "expired"),
            @Mapping(source = "credentialExpired", target = "credentialExpired"),
            @Mapping(source = "personalData", target = "personalData")
    })
    User toUser(UserDTO userDTO);

    List<UserDTO> toUserDTOList(List<User> users);
    List<User> toUserList(List<UserDTO> userDTOs);
}
