package com.hmvss.auth.persistence.mapper;

import com.hmvss.auth.dto.personalDataInfo.ParishDTO;
import com.hmvss.auth.persistence.model.Parish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = MunicipalityMapper.class)
public interface ParishMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "parishName", target = "parishName"),
            @Mapping(source = "municipality", target = "municipality")
    })
    ParishDTO toParishDTO(Parish parish);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "parishName", target = "parishName"),
            @Mapping(source = "municipality", target = "municipality")
    })
    Parish toParish(ParishDTO parishDTO);
}
