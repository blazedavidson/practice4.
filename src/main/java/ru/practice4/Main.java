package ru.practice4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.practice4.beans.*;
@SpringBootApplication(scanBasePackages = "ru.practice4")
@EnableJpaRepositories( basePackageClasses = { SelectUsers.class, SelectLogins.class})
public class Main {
    public static void main(String[] args) {
        String src = "src/main/resources";
        System.setProperty( "DB", "jdbc:postgresql://localhost:5432/postgres");
        System.setProperty( "USER", "postgres");
        System.setProperty( "PASS", "blaze");

        ApplicationContext app = SpringApplication.run(Main.class, args);
        FilesReadData fileData = app.getBean(FilesReadData.class);
        fileData.setPath(src);
        ReadFile readData = app.getBean(ReadFile.class);
        readData.invoke(src);
        FileFullNameControl nameControl = app.getBean(FileFullNameControl.class);
        nameControl.invoke();


    }
}