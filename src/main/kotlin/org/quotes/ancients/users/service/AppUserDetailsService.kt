package org.quotes.ancients.users.service

import org.quotes.ancients.users.repo.AppUserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AppUserDetailsService(
    private val repo: AppUserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val u = repo.findByUsername(username) ?: throw UsernameNotFoundException("user not found")
        val authorities = listOf(SimpleGrantedAuthority("ROLE_${u.role.name}"))
        return User(
            u.username,
            u.passwordHash ?: throw IllegalStateException("password is null for user"),
            u.enabled,
            true, true, true,
            authorities)
    }
}