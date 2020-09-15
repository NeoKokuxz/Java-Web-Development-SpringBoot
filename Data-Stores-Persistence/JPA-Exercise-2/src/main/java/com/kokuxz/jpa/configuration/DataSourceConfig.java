package com.kokuxz.jpa.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    @Primary //Without Primary will work fine too
    @ConfigurationProperties(prefix = "com.kokubyakuin.jb")
    public DataSource getDataSource(){
        DataSourceBuilder dsb = DataSourceBuilder.create();
        dsb.url("jdbc:mysql://localhost:3306/plantdb");
        return dsb.build();
    }
//    private String ps(){
//        return "sb123";
//    }
}
