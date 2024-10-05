package com.hmvss.api.util.swagger.documentation;

import com.hmvss.api.util.exceptions.ErrorDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Void.class))),
        @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = Void.class)))
})
public @interface SwaggerGenericResponses {

    Class<?> responseClass() default Default.class;

    class Default {
    }
}
