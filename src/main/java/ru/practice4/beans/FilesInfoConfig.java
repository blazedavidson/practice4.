package ru.practice4.beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


@Configuration
public class FilesInfoConfig {
    @Bean
    FilesReadData bean2() {
        return new FilesReadData();
    }
}
