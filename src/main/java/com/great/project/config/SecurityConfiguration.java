package com.great.project.config;

import com.great.project.security.handler.FailedAuthHandler;
import com.great.project.security.handler.PostLogoutHandler;
import com.great.project.security.handler.SuccessfulAuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.great.project.controller.ProductController.API_PREFIX;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain webHttpSecurity(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login", "/register", "/favicon.ico").permitAll()
                    .requestMatchers(HttpMethod.POST, API_PREFIX +"/auth/**").permitAll()
                    .anyRequest().authenticated())
            .httpBasic(httpBasic -> httpBasic.realmName("Spring Reference Project"))
            .formLogin(form -> form
                    .permitAll()
                    .defaultSuccessUrl("/", true)
                    .successHandler(successfulAuthHandler())
                    .failureHandler(failedAuthHandler()))
            .logout(logout -> logout
                    .permitAll()
                    .addLogoutHandler(postLogoutHandler()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SuccessfulAuthHandler successfulAuthHandler() {
        return new SuccessfulAuthHandler();
    }

    @Bean
    public FailedAuthHandler failedAuthHandler() {
        return new FailedAuthHandler();
    }

    @Bean
    public PostLogoutHandler postLogoutHandler() {
        return new PostLogoutHandler();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                               .username("user")
                               .password("$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW") // password
                               .roles("USER")
                               .build();
        UserDetails admin = User.builder()
                                .username("admin")
                                .password("$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW") // password
                                .roles("ADMIN")
                                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}

