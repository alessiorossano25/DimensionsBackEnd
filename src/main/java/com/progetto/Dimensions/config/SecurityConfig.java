package com.progetto.Dimensions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter, AuthenticationProvider authenticationProvider) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.authenticationProvider = authenticationProvider;
    }

@Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/cards/cardsAll").permitAll()
                        .requestMatchers("/cards/collection").authenticated()
                        .requestMatchers("/expansions/AllExpansions").permitAll()
                        .requestMatchers("/auth/check").authenticated()
                        .requestMatchers("/auth/saveUser").authenticated()
                        .requestMatchers("/expansions/myExpansion").authenticated()
                        .requestMatchers("/expansions/colorCards").authenticated()
                        .requestMatchers("/auth/changeAvatar").authenticated()
                        .anyRequest().authenticated()

                ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource (){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET","POST","OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }

}
