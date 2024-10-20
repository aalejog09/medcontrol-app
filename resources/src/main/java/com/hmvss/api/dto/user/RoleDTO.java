package com.hmvss.api.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("roleName")
    @Schema(description = "roleName", example = "Admin")
    @Size(min=5, message = "roleName cannot be less than 5 digits.")
    @NotBlank(message = "roleName cannot be null or blank.")
    private String roleName;


    @JsonProperty("description")
    @Schema(description = "description", example = "Admin role of the system")
    @Size(min=5, message = "description cannot be less than 5 digits.")
    @NotBlank(message = "description cannot be null or blank.")
    private String description;

    @JsonProperty("creationDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date creationDate;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("functions")
    private Set<FunctionDTO> functions;

}