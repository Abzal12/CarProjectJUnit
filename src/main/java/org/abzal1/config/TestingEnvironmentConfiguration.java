package org.abzal1.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestingEnvironmentConfiguration {

    @Bean
    @ConditionalOnProperty(name = "myapp.custom-bean.enabled", havingValue = "true")
    public String ThisIsMyFirstConditionalBean() {
        return "Note: It is a testing environment.";
    }
}
