package io.cloudtype.sunmoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Profile("db")
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        DaoAuthenticationProvider authenticationProvider
    ) throws Exception {
        http
            .authenticationProvider(authenticationProvider)
            .authorizeHttpRequests((authorize) -> authorize
                .antMatchers("/assets/**", "/favicon.ico", "/login", "/error").permitAll()
                .antMatchers("/", "/api/**", "/db/ping").authenticated()
                .anyRequest().denyAll()
            )
            .formLogin((formLogin) -> formLogin
                .loginPage("/login")
                .usernameParameter("loginId")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutSuccessUrl("/login?logout")
            )
            .sessionManagement(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(
        UserDetailsService userDetailsService,
        PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
