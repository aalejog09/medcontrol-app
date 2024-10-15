package com.hmvss.api.persistence.mapper;

import com.hmvss.api.dto.personalDataInfo.MunicipalityDTO;
import com.hmvss.api.persistence.model.Municipality;
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
