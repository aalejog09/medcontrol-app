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
public class ParishDTO {

    @JsonProperty("id")
    private Long  id;

    @JsonProperty("parish_name")
    private String parishName;

    @JsonProperty("municipality")
    private MunicipalityDTO municipality;


}
