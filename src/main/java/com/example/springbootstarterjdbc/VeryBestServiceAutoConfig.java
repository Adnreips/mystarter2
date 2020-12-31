package com.example.springbootstarterjdbc;

import org.example1.JdbcUtils;
import org.example1.Properties;
import org.example1.VeryBestCompanyService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Configuration
@ConditionalOnClass({VeryBestCompanyService.class})
@ConfigurationProperties(prefix = "jdbc")
public class VeryBestServiceAutoConfig {

    private String jdbcDriver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/customers";
    private String user = "root";
    private String password = "admin1982";
    private Connection connectionSave;


    @Bean
    @ConditionalOnMissingBean
    public Connection getConnection() throws SQLException {
        Properties properties = new Properties(jdbcDriver, url, user, password);
        connectionSave = JdbcUtils.getConnection(properties);
        return connectionSave;
    }

    @Bean
    @ConditionalOnMissingBean
    public VeryBestCompanyService getBestServiceConfig() throws SQLException {

        return new VeryBestCompanyService(getConnection());
    }

    @EventListener({ContextClosedEvent.class})
    void ContextClosedEvent() throws SQLException {
        connectionSave.close();
        System.out.println("Финиш");

    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnectionSave() {
        return connectionSave;
    }

    public void setConnectionSave(Connection connectionSave) {
        this.connectionSave = connectionSave;
    }
}
