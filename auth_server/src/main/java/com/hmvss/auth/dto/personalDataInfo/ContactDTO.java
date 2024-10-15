package com.hmvss.auth.dto.personalDataInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long  id;

    @JsonProperty("phone_number_principal")
    private String principalPhone;

    @JsonProperty("additional_phone_number")
    private String additionalPhone;

    @JsonProperty("email_principal")
    private String email;

    @JsonProperty("additional_email")
    private String additionalEmail;

}
