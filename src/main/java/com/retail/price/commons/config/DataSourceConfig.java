package com.retail.price.commons.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Data source config.
 */
@RequiredArgsConstructor
@Slf4j
@Configuration
public class DataSourceConfig {

  private final ConnectProperties connectProperties;

  /**
   * Gets data source.
   *
   * @return the data source
   */
  @Bean
  public DataSource getDataSource() {

    log.info("[CONNECTION] url: <{}> ", connectProperties.getDbUrl());

    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName(connectProperties.getDbDriverClass());
    dataSourceBuilder.url(connectProperties.getDbUrl());
    dataSourceBuilder.username(connectProperties.getDbUserName());
    dataSourceBuilder.password(connectProperties.getDbPassword());

    return dataSourceBuilder.build();
  }
}