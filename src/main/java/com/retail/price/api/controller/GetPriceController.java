package com.retail.price.api.controller;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.retail.commons.constants.Constants;
import com.retail.commons.utils.ApiVersion;
import com.retail.price.api.response.GetPriceResponse;
import com.retail.price.api.validator.BrandIdValid;
import com.retail.price.api.validator.ProductIdValid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Get price controller.
 */
@ApiVersion(from = Constants.VERSION_FROM)
@Tag(name = "Get price")
@RequestMapping(value = "/store/api", produces = APPLICATION_JSON_VALUE)
public interface GetPriceController {

  /**
   * Gets current price.
   *
   * @param applicationDate the application date
   * @param productId       the product id
   * @param brandId         the brand id
   * @return the current price
   */
  @Operation(
      summary = "Get price for introduced data not exists",
      parameters = {
          @Parameter(
              in = QUERY,
              name = "applicationDate",
              description = "Date on which the indicated rate price applies",
              example = "2020-06-14-00.00.00",
              schema = @Schema(implementation = LocalDateTime.class)),
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
      })
  @ApiResponses(
      value = {
          @ApiResponse(
              description = "Successful operation",
              responseCode = "200",
              content = @Content(schema = @Schema(implementation = GetPriceResponse.class))),
          @ApiResponse(
              description = "Price for introduced data not exists",
              responseCode = "204", content = @Content),
          @ApiResponse(
              description = "Bad request",
              responseCode = "400", content = @Content),
          @ApiResponse(description = "General system error", responseCode = "500", content =
          @Content)
      })
  ResponseEntity<GetPriceResponse> getCurrentPrice(@RequestHeader HttpHeaders headers,
                                                   @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
                                                   @RequestParam("productId") @ProductIdValid Long productId,
                                                   @RequestParam("brandId") @BrandIdValid Long brandId);
}