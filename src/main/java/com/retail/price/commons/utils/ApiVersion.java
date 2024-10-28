package com.retail.price.commons.utils;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Api version.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

  /**
   * From int.
   *
   * @return the int
   */
  int from();

  /**
   * To int.
   *
   * @return the int
   */
  int to() default 1;
}