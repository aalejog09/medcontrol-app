package com.hmvss.api.dto.personalDataInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("names")
    private String names;

    @JsonProperty("lastnames")
    private String lastnames;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("born_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date bornDate;

    @JsonProperty("civil_state")
    private String civilState;

    @JsonProperty("dni")
    private String identificationDocumentNumber;

    @JsonProperty("profession")
    private String profession;

    @JsonProperty("education_level")
    private String educationLevel;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("location")
    private LocationDTO location;

    @JsonProperty("contact")
    private ContactDTO contact;

    @JsonProperty("registry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date registryDate;
}
