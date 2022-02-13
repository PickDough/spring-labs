package com.example.sportevents.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JDBCTemplConfig {
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource drivarManager = new DriverManagerDataSource();

        drivarManager.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        drivarManager.setUrl("jdbc:sqlserver://localhost\\SQLEXPRESS;"
                + "databaseName=SpringJavaLibrary;"
                + "integratedSecurity=true;"
                + "encrypt=true;"
                + "trustServerCertificate=true");

        return drivarManager;
    }
}
