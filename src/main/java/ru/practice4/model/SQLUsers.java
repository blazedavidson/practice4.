package ru.practice4.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Table(name = "USERS", schema = "POSTGRES")
public class SQLUsers {
    @Id()
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "FIO")
    private String fio;
    @Override
    public String toString() {
        return "Users{" + "ID=" + id + ", USERNAME='" + username + '\'' + ", FIO='" + fio + '\'' + '}';
    }
}