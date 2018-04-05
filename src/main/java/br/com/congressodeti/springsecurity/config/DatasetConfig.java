package br.com.congressodeti.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.MySQLContainer;

@Component
public class DatasetConfig {

    @Bean
    public DataSource getDatasource() {
        String password = "maria";
        String username = "manuel";
        String databaseName = "bancodomanuel";
        MySQLContainer container = new MySQLContainer()
                .withUsername(username)
                .withPassword(password)
                .withDatabaseName(databaseName);
        container.start();

        DataSource dataSource = DataSourceBuilder.create()
                .password(password)
                .username(username)
                .driverClassName(container.getDriverClassName())
                .url(container.getJdbcUrl())
                .build();
        return dataSource;
    }
}
