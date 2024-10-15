package com.hmvss.api.dto.personalDataInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("principalPhone")
    private String principalPhone;

    @JsonProperty("additionalPhone")
    private String additionalPhone;

    @JsonProperty("email")
    private String email;

    @JsonProperty("additionalEmail")
    private String additionalEmail;
}
