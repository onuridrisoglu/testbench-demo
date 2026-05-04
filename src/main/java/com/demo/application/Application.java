package com.demo.application;

import com.demo.application.data.SamplePersonRepository;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import javax.sql.DataSource;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "testbench-demo")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    ApplicationRunner conditionalDataInitializer(DataSource dataSource,
            SamplePersonRepository repository) {
        // This bean ensures the database is only initialized when empty
        return args -> {
            if (repository.count() == 0L) {
                new ResourceDatabasePopulator(new ClassPathResource("data.sql")).execute(dataSource);
            }
        };
    }
}
