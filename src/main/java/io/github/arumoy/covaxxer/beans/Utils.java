package io.github.arumoy.covaxxer.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
