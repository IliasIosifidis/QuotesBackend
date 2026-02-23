package org.quotes.ancients.comon.config

import org.quotes.ancients.users.domain.AppUser
import org.quotes.ancients.users.domain.Role
import org.quotes.ancients.users.repo.AppUserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class CreatorBootstrap {

    @Bean
    fun seedCreator(repo: AppUserRepository, encoder: PasswordEncoder) = CommandLineRunner {
        val creatorUsername = System.getenv("CREATOR_USERNAME") ?: "ilias"
        val creatorEmail = System.getenv("CREATOR_EMAIL") ?: "iosifidis90@gmail.com"
        val creatorPassword = System.getenv("CREATOR_PASSWORD") ?: "1990"

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