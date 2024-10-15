package com.hmvss.auth.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hmvss.auth.dto.personalDataInfo.PersonalDataDTO;
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

    @JsonProperty("password")
    private String password;

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
