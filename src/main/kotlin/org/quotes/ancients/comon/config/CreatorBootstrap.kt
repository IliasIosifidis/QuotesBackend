package org.quotes.ancients.comon.config

import org.quotes.ancients.users.users.domain.AppUser
import org.quotes.ancients.users.users.domain.Role
import org.quotes.ancients.users.users.repo.AppUserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class CreatorBootstrap {

    @Bean
    fun seedCreator(repo: AppUserRepository, encoder: PasswordEncoder) = CommandLineRunner {
        val creatorUsername = System.getenv("CREATOR_USERNAME") ?: "creator"
        val creatorEmail = System.getenv("CREATOR_EMAIL") ?: "creator@god.com"
        val creatorPassword = System.getenv("CREATOR_PASSWORD") ?: throw IllegalStateException("CREATOR_PASSWORD env var is required")

        val existing = repo.findByUsername(creatorUsername)
        if (existing == null) {
            repo.save(
                AppUser(
                    username = creatorUsername,
                    email = creatorEmail,
                    passwordHash = encoder.encode(creatorPassword),
                    role = Role.CREATOR,
                    enabled = true
                )
            )
        }
    }
}