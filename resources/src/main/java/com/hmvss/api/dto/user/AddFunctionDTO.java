package com.hmvss.api.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddFunctionDTO {

    @JsonProperty("functionId")
    Long functionId;

    @JsonProperty("roleId")
    Long RoleId;
}
