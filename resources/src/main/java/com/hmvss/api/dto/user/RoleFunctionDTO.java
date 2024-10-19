package com.hmvss.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleFunctionDTO {

    private Long roleFunctionId;
    private Long roleId;
    private Long functionId;

}
