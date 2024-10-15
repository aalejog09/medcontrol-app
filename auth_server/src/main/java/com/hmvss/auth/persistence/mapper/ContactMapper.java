package com.hmvss.auth.persistence.mapper;

import com.hmvss.auth.dto.personalDataInfo.ContactDTO;
import com.hmvss.auth.persistence.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "principalPhone", target = "principalPhone"),
            @Mapping(source = "additionalPhone", target = "additionalPhone"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "additionalEmail", target = "additionalEmail")
    })
    ContactDTO toContactDTO(Contact contact);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "principalPhone", target = "principalPhone"),
            @Mapping(source = "additionalPhone", target = "additionalPhone"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "additionalEmail", target = "additionalEmail")
    })
    Contact toContact(ContactDTO contactDTO);
}