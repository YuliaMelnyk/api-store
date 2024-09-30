package com.inditex.store.repository;

import com.inditex.store.repository.model.Price;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {


  /**
   * Method searches BDD for the different prices and rates that may apply to a
   * certain product of a chain, on the indicated date
   *
   * @param brandId
   * @param productId
   * @param applicationDate
   * @return Optional<List<Price>>
   */
  @Query("SELECT p FROM prices p "
      + "WHERE p.brandId.id = :brandId "
      + "AND p.productId = :productId "
      + "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
      "ORDER BY p.priority DESC")
  List<Price> getCurrentPrice(Long brandId, Long productId, LocalDateTime applicationDate);

}