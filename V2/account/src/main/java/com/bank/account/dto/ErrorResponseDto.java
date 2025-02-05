package com.bank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Schema(
        name = "Error Response",
        description = "Error Response Details"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(
        description = "API path invoked by the customer which caused the occurrence of the error"
    )
    private String apiPath;
    @Schema(
            description = "HTTP Status code of the error"
    )
    private HttpStatus statusCode;
    @Schema(
            description = "Error message representing the cause of the error"
    )
    private String errorMessage;

    @Schema(
            description = "Time when the error occurred"
    )
    private LocalDateTime errorTime;
}
