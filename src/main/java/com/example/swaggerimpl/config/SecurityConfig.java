package com.example.swaggerimpl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@Order(50)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${springdoc.security.user}")
    private String user;

    @Value("${springdoc.security.password}")
    private String password;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        this.configureInMemoryDatabase(authenticationManagerBuilder,this.user,encoder().encode(this.password));

    }

    private void configureInMemoryDatabase(AuthenticationManagerBuilder authenticationManagerBuilder,String user,String password) throws Exception{
        authenticationManagerBuilder.inMemoryAuthentication().withUser(user).password(password).roles("SWAGGER_USER");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").hasRole("SWAGGER_USER").
                antMatchers("/swagger-ui/*").hasRole("SWAGGER_USER").
                antMatchers("/api-docs").hasRole("SWAGGER_USER").
                antMatchers("/api-docs/*").hasRole("SWAGGER_USER").
                and()
                .httpBasic().authenticationEntryPoint(new EntryPoint());
    }
}
