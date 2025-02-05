package com.bank.cards.controller;

import com.bank.cards.constant.CardsConstants;
import com.bank.cards.dto.CardsDto;
import com.bank.cards.dto.ErrorResponseDto;
import com.bank.cards.dto.ResponseDto;
import com.bank.cards.service.ICardsService;
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
        name = "CRUD APIs for Cards microservice in Bank Application",
        description = "CRUD APIs for Cards microservice in Bank Application - " +
                "create, fetch, update, delete Accounts"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CardsController {
    private final ICardsService iCardsService;
    @Operation(
            summary = "Create Card-REST API",
            description = "Create new Card  inside the Bank"
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
    public ResponseEntity<ResponseDto> createCard(
            @RequestParam("mobileNumber")
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
        iCardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card-REST API",
            description = "Fetch  Cards Details From the Bank"
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
    public ResponseEntity<CardsDto> fetchCardDetails(
            @RequestParam("mobileNumber")
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
        CardsDto cardsDto = iCardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }


    @Operation(
            summary = "Update Card-REST API",
            description = "Update  Card Details Inside the Bank"
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
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = iCardsService.updateCard(cardsDto);

        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Card-REST API",
            description = "Delete  Card Details Inside the Bank"
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
    public ResponseEntity<ResponseDto> deleteCard(
            @RequestParam("mobileNumber")
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
        boolean isDeleted = iCardsService.deleteCard(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }
}
