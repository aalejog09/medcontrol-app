package com.hmvss.api.dto.personalDataInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {

    @JsonProperty("id")
    private Long id;

    @Schema(description = "principalPhone", example = "+582120123456")
    @NotBlank(message = "principalPhone cannot be null or blank.")
    @Pattern(regexp = "^\\+[0-9]{1,4}[0-9]{10}$", message = "principalPhone must be like  +582120123456")
    @JsonProperty("principalPhone")
    private String principalPhone;

    @NotBlank(message = "additionalPhone cannot be null or blank.")
    @Schema(description = "additionalEmail", example = "correo@correo.com")
    @Email
    @JsonProperty("email")
    private String email;

    @Schema(description = "additionalPhone", example = "+582120123456")
    @JsonProperty("additionalPhone")
    @Pattern(regexp = "^\\+[0-9]{1,4}[0-9]{10}$", message = "additionalPhone must be like  +582120123456")
    private String additionalPhone;

    @Schema(description = "additionalEmail", example = "correo@correo.com")
    @Email
    @JsonProperty("additionalEmail")
    private String additionalEmail;
}
