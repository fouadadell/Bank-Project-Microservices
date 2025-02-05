package com.bank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Response",
        description = "Response details"
)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {
    @Schema(
            description = "HTTP Status code of the response"
    )
    private String statusCode;
    @Schema(
            description = "Status message of the response"
    )
    private String statusMessage;
}
