package com.retail.price.adapter.out.persistence.repository;

import com.retail.price.adapter.out.persistence.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Price repository.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

  /**
   * Method searches BDD for the different prices and returns one price It selects all allowed
   * prices. If there are more than one, it takes the most priority one. For this reason, the query
   * is optimized.
   *
   * @param brandId         the brand id
   * @param productId       the product id
   * @param applicationDate the application date
   * @return Price current price
   */
  @Query(nativeQuery = true, value = "SELECT TOP 1 * FROM prices p "
      + "WHERE p.brand_id = :brandId "
      + "AND p.product_id = :productId "
      + "AND p.start_date <= :applicationDate "
      + "AND p.end_date >= :applicationDate " +
      "ORDER BY p.priority DESC")
  Optional<Price> getCurrentPrice(Long brandId, Long productId, LocalDateTime applicationDate);

}