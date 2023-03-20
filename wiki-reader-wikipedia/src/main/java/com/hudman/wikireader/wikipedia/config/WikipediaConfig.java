package com.hudman.wikireader.wikipedia.config;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WikipediaConfig {
    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }

    @Bean
    public com.jayway.jsonpath.Configuration configurationJsonPathBean() {
        return com.jayway.jsonpath.Configuration
                .builder()
                .jsonProvider(new JacksonJsonProvider())
                .mappingProvider(new JacksonMappingProvider())
                .build();
    }
}
