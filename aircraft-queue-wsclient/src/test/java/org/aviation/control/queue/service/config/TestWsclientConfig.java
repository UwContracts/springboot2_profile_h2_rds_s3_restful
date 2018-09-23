package org.aviation.control.queue.service.config;

import org.aviation.control.queue.wsclient.config.WsClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration
@ComponentScan(basePackages= {"org.aviation.control.queue.wsclient.service.impl"})
@Import({WsClientConfig.class})
@PropertySource(
        value={"test-aircraft-control-wsclient.properties"},
        ignoreResourceNotFound = true)
@EnableEncryptableProperties
public class TestWsclientConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
    }
    
}
