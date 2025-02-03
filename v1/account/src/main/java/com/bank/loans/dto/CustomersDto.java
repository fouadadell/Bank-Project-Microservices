package com.bank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Schema(
        name = "Customers",
        description = "Customers Schema to hold customer and account information"
)
@Data
public class CustomersDto  {

    @Schema(
            description = "name of the customer",
            example = "Bank Customer"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30,
            message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email of the customer",
            example = "xTb3r@example.com"
    )
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Phone Number of the customer",
            example = "+20-01234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
    private String mobileNumber;

    @Schema(
            description = "Account Details of the customer"
    )
    private AccountsDto accountsDto;
}
