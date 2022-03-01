package com.kata.SimpleWebservice.config;

import org.dalesbred.Database;
import org.dalesbred.integration.spring.DalesbredConfigurationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

@Configuration
public class databaseConfiguration extends DalesbredConfigurationSupport {

//     @Bean
//    public DataSource getDataSource() {
//        String url = "jdbc:postgresql://localhost:5432/postgresdb?user=postgres&password=postgres";
//        return new JndiDataSourceLookup().getDataSource(url);
//    }


    @Bean
    public Database dalesbredDatabase() {
        String url = "jdbc:postgresql://localhost:5432/postgresdb";
        return Database.forUrlAndCredentials(url, "postgres", "postgres");
    }

}
