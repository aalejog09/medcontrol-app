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
public class CityDTO {

    @JsonProperty("id")
    private Long  id;

    @JsonProperty("cityName")
    private String cityName;

    @JsonProperty("capital")
    private boolean capital;

    @JsonProperty("state")
    private StateDTO state;
}
