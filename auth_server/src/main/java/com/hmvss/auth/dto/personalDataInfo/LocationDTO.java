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
public class LocationDTO {

    @JsonProperty("id")
    private Long  id;

    @JsonProperty("city")
    private CityDTO city;

    @JsonProperty("housing")
    private String housing;

    @JsonProperty("additional_location_info")
    private String additionalInfo;
}
