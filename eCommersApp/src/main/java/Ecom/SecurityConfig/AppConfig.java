package Ecom.SecurityConfig;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration cfg = new CorsConfiguration();
                        cfg.setAllowedOriginPatterns(List.of("*"));
                        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                        cfg.setAllowCredentials(true);
                        cfg.setAllowedHeaders(List.of("*"));
                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
                        cfg.setMaxAge(3600L);
                        return cfg;
                    }
                }))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/ecom/admin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/ecom/customers").permitAll()
                        .requestMatchers(HttpMethod.GET, "/ecom/signIn", "/ecom/product-reviews/**", "/ecom/products/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/ecom/orders/users/**").permitAll()
                        .requestMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/ecom/order-shippers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/ecom/product/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST,
                                "/ecom/product-reviews/**",
                                "/ecom/customer-addresses/**",
                                "/ecom/cart/**",
                                "/ecom/orders/**",
                                "/ecom/order-shipping/**",
                                "/ecom/order-payments/**"
                        ).hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/ecom/products/**", "/ecom/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,
                                "/ecom/product-reviews/**",
                                "/ecom/customer-addresses/update/**",
                                "/ecom/cart/**",
                                "/ecom/order-shipping/**"
                        ).hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE,
                                "/ecom/products/**",
                                "/ecom/product-reviews/**",
                                "/ecom/customer-addresses/delete/**",
                                "/ecom/order-shipping/**",
                                "/ecom/order-shippers/**"
                        ).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/ecom/cart/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,
                                "/ecom/customer-addresses/**",
                                "/ecom/cart/products/**",
                                "/ecom/orders/**",
                                "/ecom/order-shippers",
                                "/ecom/order-payments/**"
                        ).hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
