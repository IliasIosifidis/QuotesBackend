package org.quotes.ancients.users.users.api

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.quotes.ancients.comon.util.JwtService
import org.quotes.ancients.users.users.service.AppUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userDetailsService: AppUserDetailsService
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val header = request.getHeader("Authorization")
        if (header != null && header.startsWith("Bearer ")){
            val token = header.removePrefix("Bearer ").trim()
            val username = jwtService.parseUsername(token)
            println("Parsed username = $username")

            if (username != null && SecurityContextHolder.getContext().authentication == null){
                val userDetails = userDetailsService.loadUserByUsername(username)
                val auth = UsernamePasswordAuthenticationToken(userDetails.username, null, userDetails.authorities)
                SecurityContextHolder.getContext().authentication = auth
            }
        }
        filterChain.doFilter(request, response)
        println("Auth header = ${request.getHeader("Authorization")}")
    }
}