package com.retail.price.commons.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * Brand id validator.
 */
@Component
public class BrandIdValidator implements ConstraintValidator<BrandIdValid, Long> {

  private String message;

  @Override
  public void initialize(BrandIdValid name) {
    message = name.message();
  }

  @Override
  public boolean isValid(Long brandId, ConstraintValidatorContext context) {

    return brandId != null && brandId >= 1L;
  }
}
