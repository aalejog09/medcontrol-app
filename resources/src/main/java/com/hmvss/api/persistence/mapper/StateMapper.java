package com.hmvss.api.persistence.mapper;

import com.hmvss.api.dto.personalDataInfo.StateDTO;
import com.hmvss.api.persistence.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StateMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "stateName", target = "stateName"),
            @Mapping(source = "iso3166Code", target = "iso3166Code")
    })
    StateDTO toStateDTO(State state);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "stateName", target = "stateName"),
            @Mapping(source = "iso3166Code", target = "iso3166Code")
    })
    State toState(StateDTO stateDTO);
}
