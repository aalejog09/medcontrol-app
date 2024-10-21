package com.hmvss.api.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hmvss.api.dto.personalDataInfo.PersonalDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @JsonProperty("id")
    private Long id;

    @Schema(description = "username", example = "Venezuelan")
    @NotBlank(message = "username cannot be null or blank.")
    @Size(min=4, message = "username cannot be less than 4 digits.")
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

    @Valid
    @JsonProperty("personalData")
    private PersonalDataDTO personalData;

    @JsonProperty("role")
    private RoleDTO role;
}
