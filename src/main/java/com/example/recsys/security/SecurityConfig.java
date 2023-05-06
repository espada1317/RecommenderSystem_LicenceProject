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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                    .authorizeHttpRequests().requestMatchers("/", "/signIn", "/signUp", "/css/**", "/about","/images/**").permitAll()
                .and()
                    .authorizeHttpRequests().requestMatchers( "/movies", "/movies/**", "/tv", "/tv/**", "/anime", "/anime/**", "/books", "/books/**").hasAnyAuthority("ADMIN", "USER")
                .and()
                    .authorizeHttpRequests().requestMatchers("/profile", "/profile/**", "/preferences/**", "/saveReview").hasAuthority("USER")
                .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .usernameParameter("nickname")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher( new AntPathRequestMatcher("/logout") )
                    .logoutSuccessUrl("/signIn?logout")
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
