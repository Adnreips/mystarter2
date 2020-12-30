package com.example.springbootstarterjdbc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConditionalOnClass({VeryBestCompanyService.class})
@ConfigurationProperties(prefix = "jdbc")
public class VeryBestServiceAutoConfig {
    private String jdbcDriver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/customers";
    private String user = "root";
    private String password = "root";

}
