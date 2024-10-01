package com.inditex.store.prices.usecase.mapper;

import com.inditex.store.prices.usecase.model.PriceVO;
import com.inditex.store.repository.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceToPriceVOMapper {

  @Mapping(source = "price.brandId", target = "brandId")
  PriceVO mapToPriceVO(Price price);
}