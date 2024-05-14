package ru.practice4.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJdbcRepositories("ru.practice4.beans")
public class SQLConfiguration extends AbstractJdbcConfiguration {

    @Bean @Primary
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl( System.getProperty("DB"));
        dataSource.setUser( System.getProperty("USER"));
        dataSource.setPassword( System.getProperty("PASS"));
        return dataSource;
    }
    @Bean(name = "transactionManager")
    @Autowired
    public PlatformTransactionManager tm( DataSource ds) {
        return new DataSourceTransactionManager( ds);
    }

    @Bean
    @Autowired
    public NamedParameterJdbcOperations np(DataSource dataSource) {
        NamedParameterJdbcOperations nameParJDBC = new NamedParameterJdbcTemplate( dataSource);
        return nameParJDBC;
    }
}
