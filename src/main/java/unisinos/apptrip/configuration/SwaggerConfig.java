package unisinos.apptrip.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Trip Verse API")
                .version("1.0")
                .description("Social media for travels"))
                .servers(List.of(
                new Server().url("http://localhost:5010").description("Local server")
        ));
    }
}


