package com.hmvss.api.dto.personalDataInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDataDTO {

    @JsonProperty("id")
    private Long  id;

    @Schema(description = "names", example = "admin")
    @NotBlank(message = "names no puede estar vacio.")
    @Size(min=3, message = "names debe ser mayor a  3 digitos.")
    @JsonProperty("names")
    private String names;

    @Schema(description = "lastnames", example = "system")
    @NotBlank(message = "lastnames no puede estar vacio.")
    @Size(min=3, message = "lastnames debe ser mayor a  3 digitos.")
    @JsonProperty("lastnames")
    private String lastnames;

    @Schema(description = "sex", example = "male")
    @NotBlank(message = "sex cannot be null or blank.")
    @Size(min=4, message = "sex debe ser mayor a  4 digitos.")
    @JsonProperty("sex")
    private String sex;

    @Schema(description = "born_date", example = "05-01-1990")
    @NotNull(message = "born_date no puede estar vacio.")
    @JsonProperty("born_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate bornDate;

    @Schema(description = "civil_state", example = "married")
    @NotBlank(message = "civil_state no puede estar vacio.")
    @Size(min=4, message = "born_date debe ser mayor a 4 digitos.")
    @JsonProperty("civil_state")
    private String civilState;

    @Schema(description = "dni", example = "V-12345, E-12345")
    @NotBlank(message = "dni  no puede estar vacio.")
    @Size(min=5, message = "dni debe ser mayor a 5 caracteres.")
    @Pattern(regexp = "^[VEP]-[0-9]{5,12}$", message = "DNI debe iniciar con las letras [V], [E] o [P] seguido de un [-] y debe ser mayor a 5 digitos.")
    @JsonProperty("dni")
    private String identificationDocumentNumber;

    @Schema(description = "profession", example = "Engineer")
    @NotBlank(message = "profession no puede estar vacio")
    @Size(min=4, message = "profession debe ser mayor a 5 digitos")
    @JsonProperty("profession")
    private String profession;

    @Schema(description = "education_level", example = "Bachelor")
    @NotBlank(message = "education_level no puede estar vacio.")
    @Size(min=5, message = "education_level debe ser mayor a 5 digitos")
    @JsonProperty("education_level")
    private String educationLevel;

    @Schema(description = "occupation", example = "Professor")
    @NotBlank(message = "occupation no puede estar vacio.")
    @Size(min=5, message = "occupation debe ser mayor a 5 digitos")
    @JsonProperty("occupation")
    private String occupation;

    @Schema(description = "nationality", example = "Venezuelan")
    @NotBlank(message = "nationality no puede estar vacio.")
    @Size(min=4, message = "nationality debe ser mayor a 5 digitos.")
    @JsonProperty("nationality")
    private String nationality;


    @JsonProperty("registry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date registryDate;

    @Valid
    @JsonProperty("location")
    private LocationDTO location;

    @Valid
    @JsonProperty("contact")
    private ContactDTO contact;


}
