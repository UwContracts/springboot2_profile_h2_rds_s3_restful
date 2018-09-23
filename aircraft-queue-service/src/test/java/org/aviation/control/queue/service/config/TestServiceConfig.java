package org.aviation.control.queue.service.config;

import org.aviation.control.queue.domain.config.DomainConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages= {"org.aviation.control.queue.service.impl", "org.aviation.control.queue.service.util"})
@Import({DomainConfig.class})
@PropertySource(
        value={"classpath:test-aircraft-control-db.properties"},
        ignoreResourceNotFound = true)
public class TestServiceConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
    }
    
}
