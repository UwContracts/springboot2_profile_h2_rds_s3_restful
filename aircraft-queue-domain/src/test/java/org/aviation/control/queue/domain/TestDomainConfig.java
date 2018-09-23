package org.aviation.control.queue.domain;

import org.aviation.control.queue.domain.config.DomainConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({DomainConfig.class})
@PropertySource(
        value={"classpath:test-aircraft-control-db.properties"},
        ignoreResourceNotFound = true)
public class TestDomainConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
    }
    
}
