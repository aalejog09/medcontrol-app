package com.hmvss.auth.persistence.mapper;

import com.hmvss.auth.dto.personalDataInfo.MunicipalityDTO;
import com.hmvss.auth.persistence.model.Municipality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = StateMapper.class)
public interface MunicipalityMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "municipalityName", target = "municipalityName"),
            @Mapping(source = "state", target = "state")

    })
    MunicipalityDTO toMunicipalityDTO(Municipality municipality);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "municipalityName", target = "municipalityName"),
            @Mapping(source = "state", target = "state")

    })
    Municipality toMunicipality(MunicipalityDTO municipalityDTO);
}
