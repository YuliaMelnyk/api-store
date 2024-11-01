package com.retail.price.adapter.out.persistence.repository;

import com.retail.price.adapter.out.persistence.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Brand repository.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}