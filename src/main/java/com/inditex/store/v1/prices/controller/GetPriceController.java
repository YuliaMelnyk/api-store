package com.inditex.store.v1.prices.controller;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.inditex.store.commons.constants.Constants;
import com.inditex.store.commons.utils.ApiVersion;
import com.inditex.store.v1.prices.controller.model.GetPriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ApiVersion(from = Constants.VERSION_FROM)
@Tag(name = "Get prices")
@RequestMapping(value = "/v1", produces = APPLICATION_JSON_VALUE)
public interface GetPriceController {

  @Operation(
      summary = "Create customer",
      description =
          "This operation init the process to store a new customer in the Openbank systems",
      parameters = {
          @Parameter(
              in = QUERY,
              name = "startDate",
              description = "Date on which the indicated rate price applies",
              example = "2020-06-14-00.00.00",
              schema = @Schema(implementation = ZonedDateTime.class)),
          @Parameter(
              in = QUERY,
              name = "productId",
              description = "Product code identifier",
              example = "35455",
              schema = @Schema(implementation = String.class)),
          @Parameter(
              in = QUERY,
              name = "brandId",
              description = "Brand identifier",
              example = "1",
              schema = @Schema(implementation = Long.class))
      },
      responses =
      @ApiResponse(
          description = "Successful operation",
          responseCode = "200",
          content = @Content(schema = @Schema(implementation = GetPriceResponse.class))))
  ResponseEntity<GetPriceResponse> getCurrentPrice(
      @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
      @RequestParam("productId") Long productId,
      @RequestParam("brandId") Long brandId);
}