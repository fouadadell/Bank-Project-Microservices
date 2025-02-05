package com.bank.loans.controller;

import com.bank.loans.constant.LoansConstants;
import com.bank.loans.dto.LoansDto;
import com.bank.loans.dto.ErrorResponseDto;
import com.bank.loans.dto.ResponseDto;
import com.bank.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD APIs for Loans microservice in Bank Application",
        description = "CRUD APIs for Loans microservice in Bank Application - " +
                "create, fetch, update, delete Loans"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class LoansController {
    private final ILoansService iLoansService;
    @Operation(
            summary = "Create Loan-REST API",
            description = "Create new Loan  inside the Bank"
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
    public ResponseEntity<ResponseDto> createLoan(
            @RequestParam("mobileNumber")
            @Pattern(regexp="(^$|[0-9]{11})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Loan-REST API",
            description = "Fetch  Loan Details From the Bank"
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
    public ResponseEntity<LoansDto> fetchLoanDetails(
            @RequestParam("mobileNumber")
            @Pattern(regexp="(^$|[0-9]{11})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
        LoansDto loansDto = iLoansService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }


    @Operation(
            summary = "Update Loan-REST API",
            description = "Update  Loan Details Inside the Bank"
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
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody LoansDto loansDto) {
        boolean isUpdated = iLoansService.updateLoan(loansDto);

        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Loan-REST API",
            description = "Delete  Loan Details Inside the Bank"
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
    public ResponseEntity<ResponseDto> deleteLoan(
            @RequestParam("mobileNumber")
            @Pattern(regexp="(^$|[0-9]{11})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }
}
