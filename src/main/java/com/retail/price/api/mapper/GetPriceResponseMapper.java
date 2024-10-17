package com.retail.price.api.mapper;

import com.retail.price.api.response.GetPriceResponse;
import com.retail.price.domain.model.PriceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Get price response mapper.
 */
@Mapper(componentModel = "spring")
public abstract class GetPriceResponseMapper {

  /**
   * Map to get price response get price response.
   *
   * @param priceVO the price vo
   * @return the get price response
   */
  @Mapping(target = "brandId", source = "priceVO.brandId.id")
  public abstract GetPriceResponse mapToGetPriceResponse(PriceVO priceVO);
}