package com.bank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Account Schema to hold account information"
)
@Data
public class AccountsDto  {

    @Schema(
            description = "Customer's Account Number",
            example = "+20-1234567890"
    )
    private Long accountsNumber;
    @Schema(
            description = "Customer's Account Type",
            example = "Savings"
    )
    private String accountType;

    @Schema(
            description = "Customer's Branch Address"
    )
    private String branchAddress;
}
