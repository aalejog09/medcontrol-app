package com.hmvss.api.dto.user;

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
public class RoleDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String roleName;

    @JsonProperty("creationDate")
    private Date creationDate;

    @JsonProperty("enabled")
    private boolean enabled;

}