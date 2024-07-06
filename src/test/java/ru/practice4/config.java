package ru.practice4;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@PropertySource("classpath:h2.properties")
@EnableTransactionManagement
public class config {
    @Autowired
    private Environment env;
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        try {
            Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement();
            st.execute( "CREATE SCHEMA IF NOT EXISTS POSTGRES");
            st.execute( "SET SCHEMA POSTGRES");
            st.execute( "CREATE TABLE USERS( ID int auto_increment PRIMARY KEY, USERNAME varchar(50), FIO varchar(100) );");
            st.execute( "CREATE TABLE LOGINS(ID int auto_increment PRIMARY KEY, ACCESS_DATE date NOT NULL, USER_ID int , APPLICATION varchar(100), CONSTRAINT fk_id FOREIGN KEY (USER_ID)  REFERENCES users(ID) );");
        }
        catch ( SQLException ex) { throw new RuntimeException( "table not created!"); }
        return dataSource;
    }
}
