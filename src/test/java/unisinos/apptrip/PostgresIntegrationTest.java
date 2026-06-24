package unisinos.apptrip;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

abstract class PostgresIntegrationTest {

    private static final String EXTERNAL_URL = System.getenv("APPTRIP_TEST_DB_URL");
    private static final PostgreSQLContainer<?> POSTGRES;

    static {
        if (EXTERNAL_URL == null || EXTERNAL_URL.isBlank()) {
            POSTGRES = new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("apptrip")
                    .withUsername("apptrip")
                    .withPassword("apptrip");
            POSTGRES.start();
        } else {
            POSTGRES = null;
        }
    }

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        if (POSTGRES != null) {
            registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
            registry.add("spring.datasource.username", POSTGRES::getUsername);
            registry.add("spring.datasource.password", POSTGRES::getPassword);
        } else {
            registry.add("spring.datasource.url", () -> EXTERNAL_URL);
            registry.add("spring.datasource.username",
                    () -> System.getenv().getOrDefault("APPTRIP_TEST_DB_USERNAME", "apptrip"));
            registry.add("spring.datasource.password",
                    () -> System.getenv().getOrDefault("APPTRIP_TEST_DB_PASSWORD", "apptrip"));
        }
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
        registry.add("spring.jpa.properties.hibernate.show_sql", () -> "false");
        registry.add("spring.flyway.enabled", () -> "true");
        registry.add("apptrip.admin.email", () -> "");
    }
}
