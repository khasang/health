package org.health.config;

import org.health.model.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Dog dog() {
        // any logic
        return new Dog("Sharik", "sweet");
    }
}
