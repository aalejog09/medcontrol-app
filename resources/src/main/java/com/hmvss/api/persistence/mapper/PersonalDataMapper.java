package com.hmvss.api.persistence.mapper;

import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import com.hmvss.api.dto.user.UserDTO;
import com.hmvss.api.persistence.model.PersonalData;
import com.hmvss.api.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

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

    List<PersonalDataDTO> toPersonalDataDTOList(List<PersonalData> personalDataList);
    List<PersonalData> toPersonalDataList(List<PersonalDataDTO> personalDataDTOList);
}
