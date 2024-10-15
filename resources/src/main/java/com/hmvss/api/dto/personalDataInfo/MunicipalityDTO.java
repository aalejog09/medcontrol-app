package com.hmvss.api.dto.personalDataInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MunicipalityDTO {

    @JsonProperty("id")
    private Long  id;

    @JsonProperty("state")
    private StateDTO state;

    @JsonProperty("municipality_name")
    private String municipalityName;
}
