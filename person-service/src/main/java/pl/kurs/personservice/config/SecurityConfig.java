package pl.kurs.personservice.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.kurs.personservice.service.AppUserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AppUserService appUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .userDetailsService(appUserService)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/people").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/people").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/people/import").hasAnyRole("ADMIN", "IMPORTER")
                .requestMatchers(HttpMethod.POST, "/api/employees/*/positions").hasAnyRole("ADMIN", "EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}