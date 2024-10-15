package com.hmvss.auth.persistence.mapper;

import com.hmvss.auth.dto.personalDataInfo.LocationDTO;
import com.hmvss.auth.persistence.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = CityMapper.class)
public interface LocationMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "housing", target = "housing"),
            @Mapping(source = "additionalInfo", target = "additionalInfo"),
            @Mapping(source = "city", target = "city")
    })
    LocationDTO toLocationDTO(Location location);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "housing", target = "housing"),
            @Mapping(source = "additionalInfo", target = "additionalInfo"),
            @Mapping(source = "city", target = "city")
    })
    Location toLocation(LocationDTO locationDTO);
}
