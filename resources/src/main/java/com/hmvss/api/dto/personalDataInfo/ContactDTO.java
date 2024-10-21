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
    @NotBlank(message = "principalPhone no puede estar vacio.")
    @Pattern(regexp = "^\\+[0-9]{1,4}[0-9]{10}$", message = "principalPhone  debe ser valido. Ejm: +582120123456")
    @JsonProperty("principalPhone")
    private String principalPhone;

    @NotBlank(message = "email no puede estar vacio.")
    @Schema(description = "email", example = "correo@correo.com")
    @Email(message = "email debe ser una direccion de correo valida. Ejm:correo@gmail.com")
    @JsonProperty("email")
    private String email;

    @Schema(description = "additionalPhone", example = "+582120123456")
    @JsonProperty("additionalPhone")
    private String additionalPhone;

    @Schema(description = "additionalEmail", example = "correo@correo.com")
    @Email(message = "additionalEmail debe ser una direccion de correo valida. Ejm:correo@gmail.com")
    @JsonProperty("additionalEmail")
    private String additionalEmail;
}
