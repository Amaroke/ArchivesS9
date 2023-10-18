package amaroke.exofinal.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("ExoFinal - API").pathsToMatch("/**").build();
    }
}
