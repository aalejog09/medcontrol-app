package com.hmvss.api.dto.personalDataInfo;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "housing no puede estar vacio")
    @JsonProperty("housing")
    private String housing;

    @JsonProperty("additional_location_info")
    private String additionalInfo;

    @JsonProperty("city")
    private CityDTO city;
}
