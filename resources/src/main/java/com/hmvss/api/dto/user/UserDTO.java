package com.hmvss.api.dto.user;

import com.hmvss.api.util.converters.BooleanToSmallintConverter;
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


/*
    private String firstName;

    private String lastName;

    private String email;
*/

    private String username;

    @Convert(converter = BooleanToSmallintConverter.class)
    private boolean locked;

    @Convert(converter = BooleanToSmallintConverter.class)
    private boolean credentialExpired ;

    @Convert(converter = BooleanToSmallintConverter.class)
    private boolean expired ;


}
