package com.hmvss.auth.persistence.mapper;

import com.hmvss.auth.dto.personalDataInfo.CityDTO;
import com.hmvss.auth.persistence.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = StateMapper.class)
public interface CityMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "cityName", target = "cityName"),
            @Mapping(source = "capital", target = "capital"),
            @Mapping(source = "state", target = "state")
    })
    CityDTO toCityDTO(City city);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "cityName", target = "cityName"),
            @Mapping(source = "capital", target = "capital"),
            @Mapping(source = "state", target = "state")
    })
    City toCity(CityDTO cityDTO);
}
