package configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Trip Verse API")
                .version("1.0")
                .description("Social media for travels"));
    }
}

