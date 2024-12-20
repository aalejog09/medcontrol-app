package com.hmvss.api.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {

    @Valid
    @JsonProperty("personalData")
    public PersonalDataDTO personalDataDTO;

    @Valid
    @JsonProperty("roleId")
    public Long roleId;

}
