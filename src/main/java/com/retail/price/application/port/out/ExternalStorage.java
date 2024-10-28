package com.retail.price.application.port.out;

import com.retail.price.application.domain.model.PriceVO;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ExternalStorage {

  Optional<PriceVO> getCurrentPrice(Long brandId, Long productId, LocalDateTime applicationDate);
}
