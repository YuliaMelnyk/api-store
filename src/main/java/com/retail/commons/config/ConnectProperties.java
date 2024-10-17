package com.retail.commons.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Connect properties.
 */
@Component
@ConfigurationProperties
@Getter
@Setter
public class ConnectProperties {

  private String dbType;

  private String dbDriverClass;

  private String dbUrl;

  private String dbUserName;

  private String dbPassword;

  private String dbTableName;
}
