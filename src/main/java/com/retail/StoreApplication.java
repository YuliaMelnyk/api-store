package com.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Store application.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.retail"})
public class StoreApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(StoreApplication.class, args);
  }

}