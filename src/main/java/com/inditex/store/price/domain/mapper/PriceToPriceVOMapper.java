package com.inditex.store.price.domain.mapper;

import com.inditex.store.price.adapter.repository.model.Price;
import com.inditex.store.price.domain.model.PriceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceToPriceVOMapper {

  @Mapping(source = "price.brandId", target = "brandId")
  PriceVO mapToPriceVO(Price price);
}