package com.hmvss.auth.persistence.mapper;

import com.hmvss.auth.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.auth.persistence.model.PersonalData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {LocationMapper.class, ContactMapper.class})
public interface PersonalDataMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "names", target = "names"),
            @Mapping(source = "lastnames", target = "lastnames"),
            @Mapping(source = "sex", target = "sex"),
            @Mapping(source = "bornDate", target = "bornDate"),
            @Mapping(source = "civilState", target = "civilState"),
            @Mapping(source = "identificationDocumentNumber", target = "identificationDocumentNumber"),
            @Mapping(source = "profession", target = "profession"),
            @Mapping(source = "educationLevel", target = "educationLevel"),
            @Mapping(source = "occupation", target = "occupation"),
            @Mapping(source = "nationality", target = "nationality"),
            @Mapping(source = "registryDate", target = "registryDate"),
            @Mapping(source = "location", target = "location"),
            @Mapping(source = "contact", target = "contact")
    })
    PersonalDataDTO toPersonalDataDTO(PersonalData personalData);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "names", target = "names"),
            @Mapping(source = "lastnames", target = "lastnames"),
            @Mapping(source = "sex", target = "sex"),
            @Mapping(source = "bornDate", target = "bornDate"),
            @Mapping(source = "civilState", target = "civilState"),
            @Mapping(source = "identificationDocumentNumber", target = "identificationDocumentNumber"),
            @Mapping(source = "profession", target = "profession"),
            @Mapping(source = "educationLevel", target = "educationLevel"),
            @Mapping(source = "occupation", target = "occupation"),
            @Mapping(source = "nationality", target = "nationality"),
            @Mapping(source = "registryDate", target = "registryDate"),
            @Mapping(source = "location", target = "location"),
            @Mapping(source = "contact", target = "contact")
    })
    PersonalData toPersonalData(PersonalDataDTO personalDataDTO);
}
