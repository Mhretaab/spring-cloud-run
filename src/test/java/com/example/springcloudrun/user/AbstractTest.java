package com.example.springcloudrun.user;

import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public class AbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);
    private static PostgreSQLContainer postgresDb = new PostgreSQLContainer<>("postgres:latest")
            .withReuse(true);

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresDb::getJdbcUrl);
        registry.add("spring.datasource.username", postgresDb::getUsername);
        registry.add("spring.datasource.password", postgresDb::getPassword);

        LOGGER.info("Test Container DB Name: {}", postgresDb.getDatabaseName());
        LOGGER.info("Test Container DB Username: {}", postgresDb.getUsername());
        LOGGER.info("Test Container DB Password: {}", postgresDb.getPassword());
        LOGGER.info("Test Container DB Exposed Port: {}", postgresDb.getFirstMappedPort());
    }

    @BeforeAll
    static void setUp() {
        postgresDb.start();
    }
}