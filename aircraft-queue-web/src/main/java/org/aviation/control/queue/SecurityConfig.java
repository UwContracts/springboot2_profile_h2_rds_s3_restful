package org.aviation.control.queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${admin_auth_username}")
    private String username;
    
    @Value("${admin_auth_password}")
    private String password;
    
	//Class to define which pages are permitted for security perspective and flow for login scenarios
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/", "/boot", "/enqueue", "/dequeue", "/api/*").permitAll()
        .anyRequest().permitAll();
    }
    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder().password(password).username(username).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user);
    }
    
}
