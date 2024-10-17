package com.retail.price.api.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Brand id valid.
 */
@Documented
@Constraint(validatedBy = BrandIdValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface BrandIdValid {

  /**
   * Message string.
   *
   * @return the string
   */
  String message() default "idx_brand_id_invalid";

  /**
   * Groups class [ ].
   *
   * @return the class [ ]
   */
  Class<?>[] groups() default {};

  /**
   * Payload class [ ].
   *
   * @return the class [ ]
   */
  Class<? extends Payload>[] payload() default {};
}
