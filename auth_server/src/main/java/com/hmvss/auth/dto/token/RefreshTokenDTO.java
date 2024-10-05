package com.hmvss.auth.dto.token;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RefreshTokenDTO {

    @Schema(description = "Refresh token", example = "ayTU7La2g_DDgKD1MI0Kp4_j3hE9GkVDLjPZabZ8JXPr4XhhrJqe8sm4OwDLIgBORsefkviaF124etxzS3yH6V3sKffby8K8t2iuO3X09KFXT-73xnEIK2bgULkqpEdb")
    @NotBlank(message = "RefreshToken cannot be null or blank.")
    @Size(min=100, message = "RefreshToken cannot be less than 100 digits.")
    String refreshToken;

}
