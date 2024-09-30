package com.inditex.store.usecase.mapper;

import com.inditex.store.repository.model.Price;
import com.inditex.store.usecase.model.PriceVO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  List<PriceVO> priceToPriceVO(List<Price> prices);
}