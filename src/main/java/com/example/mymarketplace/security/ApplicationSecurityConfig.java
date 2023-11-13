package com.example.mymarketplace.security;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configurable
@EnableWebSecurity
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .dispatcherTypeMatchers(HttpMethod.GET, DispatcherType.valueOf("/api/clients/**")).hasRole("CLIENT")
                        .dispatcherTypeMatchers(HttpMethod.GET, DispatcherType.valueOf("/api/users/**")).hasRole("USER")
                        .dispatcherTypeMatchers(HttpMethod.GET, DispatcherType.valueOf("/api/telephones/**")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();
        UserDetails client = User.builder()
                .username("client")
                .password(passwordEncoder.encode("client"))
                .roles("CLIENT")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, client, admin);
    }

}

//.authorizeRequests(authorize -> authorize
//        .dispatcherTypeMatchers(HttpMethod.valueOf("/api/clients/**")).hasRole("CLIENT")
//        .dispatcherTypeMatchers(HttpMethod.valueOf("/api/users/**")).hasRole("USER")
//        .dispatcherTypeMatchers(HttpMethod.valueOf("/api/telephones/**")).hasRole("ADMIN")
//        .anyRequest().authenticated()
//        )
//        .formLogin(formLogin -> formLogin
//        .loginPage("/login")
//        .permitAll()
//        );
