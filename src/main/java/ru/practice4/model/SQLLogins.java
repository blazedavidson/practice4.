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

@Table(name = "LOGINS", schema = "POSTGRES")
public class SQLLogins {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "ID")
    long id;
    @Column(name = "ACCESS_DATE")
    Date accessDate;
    @Column(name = "USER_ID")
    @ManyToOne(targetEntity = SQLUsers.class)
    long userId;
    @Column(name = "APPLICATION")
    String application;
    @Override
    public String toString() {
        return "Logins{" + "ID=" + id + ", USER_ID=" + userId + '\'' + ", ACCESS_DATE='" + accessDate + '\'' + ", APPLICATION='" + application + '\'' + '}';
    }
}

