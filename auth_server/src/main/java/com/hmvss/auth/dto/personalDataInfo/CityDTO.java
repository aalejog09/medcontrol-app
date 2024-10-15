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
public class CityDTO {

    @JsonProperty("id")
    private Long  id;

    @JsonProperty("state")
    private StateDTO state;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("capital")
    private boolean capital;
}
