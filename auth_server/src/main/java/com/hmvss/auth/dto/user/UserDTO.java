package com.hmvss.auth.dto.user;

import com.hmvss.auth.util.converters.BooleanToSmallintConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {



    private String username;

    private String password;

    @Convert(converter = BooleanToSmallintConverter.class)
    private boolean enabled = false;


}
