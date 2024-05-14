package ru.practice4.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter

@Table(name = "logins", schema = "postgres")
public class SQLLogins {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @Column(name = "access_date")
    Date accessDate;
    @Column(name = "user_id")
    @ManyToOne(targetEntity = SQLUsers.class)
    long userId;
    @Column(name = "application")
    String application;
    @Override
    public String toString() {
        return "Logins{" + "id=" + id + ", user_id=" + userId + '\'' + ", access_date='" + accessDate + '\'' + ", application='" + application + '\'' + '}';
    }
}

