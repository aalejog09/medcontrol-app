package com.hmvss.auth.persistence.mapper;

import com.hmvss.auth.dto.user.UserDTO;
import com.hmvss.auth.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = PersonalDataMapper.class)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password"),
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
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "enabled", target = "enabled"),
            @Mapping(source = "locked", target = "locked"),
            @Mapping(source = "expired", target = "expired"),
            @Mapping(source = "credentialExpired", target = "credentialExpired"),
            @Mapping(source = "personalData", target = "personalData")
    })
    User toUser(UserDTO userDTO);
}
