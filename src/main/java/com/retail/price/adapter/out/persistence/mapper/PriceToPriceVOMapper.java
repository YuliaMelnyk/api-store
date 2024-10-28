package com.retail.price.adapter.out.persistence.mapper;

import com.retail.price.adapter.out.persistence.model.Price;
import com.retail.price.application.domain.model.PriceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The interface Price to price vo mapper.
 */
@Mapper(componentModel = "spring")
public interface PriceToPriceVOMapper {

  /**
   * Map to price vo price vo.
   *
   * @param price the price
   * @return the price vo
   */
  @Mapping(source = "price.brandId", target = "brandId")
  PriceVO mapToPriceVO(Price price);
}