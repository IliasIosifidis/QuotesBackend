package org.quotes.ancients.users.users.repo

import org.quotes.ancients.users.users.domain.AppUser
import org.springframework.data.jpa.repository.JpaRepository

interface AppUserRepository: JpaRepository<AppUser, Long> {
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
    fun findByUsername(username: String): AppUser?
}