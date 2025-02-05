package com.bank.account.controller;

import com.bank.account.constant.AccountsConstants;
import com.bank.account.dto.CustomersDto;
import com.bank.account.dto.ErrorResponseDto;
import com.bank.account.dto.ResponseDto;
import com.bank.account.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD APIs for Accounts microservice in Bank Application",
        description = "CRUD APIs for Accounts microservice in Bank Application - " +
                "create, fetch, update, delete Accounts"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountController {
    private final IAccountsService iAccountsService;

    @Operation(
            summary = "Create Account-REST API",
            description = "Create new Account and Customer inside the Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status - Created"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status -Bad Request",
                    content =@Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status -INTERNAL_SERVER_ERROR",
                    content =@Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomersDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account-REST API",
            description = "Fetch  Account Details From the Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status -OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status -NOT_FOUND",
                    content =@Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status -INTERNAL_SERVER_ERROR",
                    content =@Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )

    })

    @GetMapping("/fetch")
    public ResponseEntity<CustomersDto> fetchAccountDetails(
            @RequestParam("email")  @Email
            String email) {
        CustomersDto customerDto = iAccountsService.getAccount(email);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }


    @Operation(
            summary = "Update Account-REST API",
            description = "Update  Account Details Inside the Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status-OK"
    ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status-EXPECTATION_FAILED"

    ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTp Status - NOT_FOUND",
                    content =@Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status -INTERNAL_SERVER_ERROR",
                    content =@Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )

    })

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomersDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);

        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account-REST API",
            description = "Delete  Account Details Inside the Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status- OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status-EXPECTATION_FAILED"
                    ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTp Status - NOT_FOUND",
                    content =@Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status -INTERNAL_SERVER_ERROR",
                    content =@Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String email) {
        boolean isDeleted = iAccountsService.deleteAccount(email);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
}
