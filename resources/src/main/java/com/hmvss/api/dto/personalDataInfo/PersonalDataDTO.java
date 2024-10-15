package com.hmvss.api.dto.personalDataInfo;

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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDataDTO {

    @JsonProperty("id")
    private Long  id;

    @Schema(description = "names", example = "admin")
    @NotBlank(message = "names cannot be null or blank.")
    @Size(min=3, message = "names cannot be less than 3 digits.")
    @JsonProperty("names")
    private String names;

    @Schema(description = "lastnames", example = "system")
    @NotBlank(message = "lastnames cannot be null or blank.")
    @Size(min=3, message = "lastnames cannot be less than 3 digits.")
    @JsonProperty("lastnames")
    private String lastnames;

    @Schema(description = "sex", example = "male")
    @NotBlank(message = "sex cannot be null or blank.")
    @Size(min=4, message = "sex cannot be less than 4 digits.")
    @JsonProperty("sex")
    private String sex;

    @Schema(description = "born_date", example = "05/01/1990")
    @NotBlank(message = "born_date cannot be null or blank.")
    @Size(min=10, message = "born_date cannot be less than 10 digits.")
    @JsonProperty("born_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date bornDate;

    @Schema(description = "civil_state", example = "married")
    @NotBlank(message = "civil_state cannot be null or blank.")
    @Size(min=4, message = "born_date cannot be less than 4 digits.")
    @JsonProperty("civil_state")
    private String civilState;

    @Schema(description = "dni", example = "V-12345, E-12345")
    @NotBlank(message = "dni cannot be null or blank.")
    @Size(min=7, message = "dni cannot be less than 7 digits.")
    @JsonProperty("dni")
    private String identificationDocumentNumber;

    @Schema(description = "profession", example = "Engineer")
    @NotBlank(message = "profession cannot be null or blank.")
    @Size(min=4, message = "profession cannot be less than 4 digits.")
    @JsonProperty("profession")
    private String profession;

    @Schema(description = "education_level", example = "Bachelor")
    @NotBlank(message = "education_level cannot be null or blank.")
    @Size(min=4, message = "education_level cannot be less than 4 digits.")
    @JsonProperty("education_level")
    private String educationLevel;

    @Schema(description = "occupation", example = "Professor")
    @NotBlank(message = "occupation cannot be null or blank.")
    @Size(min=4, message = "occupation cannot be less than 4 digits.")
    @JsonProperty("occupation")
    private String occupation;

    @Schema(description = "nationality", example = "Venezuelan")
    @NotBlank(message = "nationality cannot be null or blank.")
    @Size(min=4, message = "nationality cannot be less than 4 digits.")
    @JsonProperty("nationality")
    private String nationality;

    @NotBlank(message = "nationality cannot be null or blank.")
    @JsonProperty("location")
    private LocationDTO location;

    @NotBlank(message = "nationality cannot be null or blank.")
    @JsonProperty("contact")
    private ContactDTO contact;


    @JsonProperty("registry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date registryDate;
}
