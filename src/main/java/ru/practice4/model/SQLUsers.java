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

@Table(name = "users", schema = "postgres")
public class SQLUsers {
    @Id()
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "fio")
    private String fio;
    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", username='" + username + '\'' + ", fio='" + fio + '\'' + '}';
    }
}