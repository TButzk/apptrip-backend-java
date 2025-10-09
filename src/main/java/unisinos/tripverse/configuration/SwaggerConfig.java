package unisinos.tripverse.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Trip Verse API")
                .version("1.0")
                .description("Social media for travels"))
                .servers(List.of(
                new Server().url("http://localhost:8080").description("Local server")
        ));
    }
}

