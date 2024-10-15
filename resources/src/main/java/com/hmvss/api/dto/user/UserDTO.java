package com.hmvss.api.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    @JsonProperty("id")
    private Long id;


    @JsonProperty("username")
    private String username;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("locked")
    private boolean locked;

    @JsonProperty("expired")
    private boolean expired;

    @JsonProperty("credentialExpired")
    private boolean credentialExpired;

    @JsonProperty("personalData")
    private PersonalDataDTO personalData;
}
