package com.example.recsys.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                    .authorizeHttpRequests().requestMatchers("/signIn","/signUp").permitAll()
                .and()
                    .authorizeHttpRequests().requestMatchers("/movies/getById/**").hasAnyAuthority("ADMIN", "USER")
                .and()
                    .authorizeHttpRequests().requestMatchers("/movies").hasAnyAuthority("ADMIN", "USER")
                .and()
                    .authorizeHttpRequests().requestMatchers("/movies/getByTitle/**").hasAuthority("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .usernameParameter("nickname")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/movies")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/signIn?logout")
                    .permitAll()
                .and()
                    .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
