package com.hmvss.auth.dto.personalDataInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StateDTO {

    @JsonProperty("id")
    private Long  id;

    @JsonProperty("stateName")
    private String stateName;

    @JsonProperty("iso_3166_2_code")
    private String iso3166Code;
}
