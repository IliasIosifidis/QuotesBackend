package org.quotes.ancients.comon.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .httpBasic {  }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(HttpMethod.GET, "/api/user-quotes/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/user-quotes/**").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/api/user-quotes/**").hasAnyRole("ADMIN","CREATOR")
                    .anyRequest().denyAll()
            }
        return http.build()
    }
}