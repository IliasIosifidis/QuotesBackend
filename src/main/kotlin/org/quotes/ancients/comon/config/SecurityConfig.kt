package org.quotes.ancients.comon.config

import org.quotes.ancients.comon.util.JwtService
import org.quotes.ancients.users.users.api.JwtAuthFilter
import org.quotes.ancients.users.users.service.AppUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtService: JwtService,
    private val userDetailsService: AppUserDetailsService
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth ->
                auth
                    // Public APIs
                    .requestMatchers("/api/latins/**").permitAll()
                    .requestMatchers("/api/chinese/**").permitAll()
                    .requestMatchers("/api/greeks/**").permitAll()
                    // User Administration
                    .requestMatchers("/error").permitAll()
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/auth/users/**").hasAnyRole("CREATOR")
                    // Quotes
                    .requestMatchers(HttpMethod.GET, "/api/user-quotes/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/user-quotes/**").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/api/user-quotes/**").hasAnyRole("ADMIN","CREATOR")
                    .anyRequest().denyAll()
            }
            .addFilterBefore(
                JwtAuthFilter(jwtService, userDetailsService),
                UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}