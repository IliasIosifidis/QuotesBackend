package org.quotes.ancients.users.service

import jakarta.transaction.Transactional
import org.quotes.ancients.users.domain.AppUser
import org.quotes.ancients.users.domain.Role
import org.quotes.ancients.users.repo.AppUserRepository
import org.springframework.stereotype.Service
import org.springframework.security.crypto.password.PasswordEncoder

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
)

@Service
class UserService(
    private val repo: AppUserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun register(request: RegisterRequest): AppUser{
        val username = request.username.trim()
        val email = request.email.trim()
        require(username.isNotBlank()) {"username should not be blank"}
        require(email.isNotBlank()) {"email is required"}
        require(request.password.length >= 8) {"password must be at least 8 characters long"}

        if (repo.existsByUsername(username)) error("username already exists")
        if (repo.existsByEmail(email)) error("email already exists")

        val user = AppUser(
            username = username,
            email = email,
            passwordHash = passwordEncoder.encode(request.password),
            role = Role.USER,
            enabled = true
        )

        return repo.save(user)
    }

    @Transactional
    fun setRole(userId: Long, newRole: Role, actorRole: Role){
        if (actorRole != Role.CREATOR) error("Only the creator can change roles")
        if (newRole == Role.CREATOR) error("Cannot assign anybody as a creator")

        val user = repo.findById(userId).orElseThrow {error("user not found")}
        user.role = newRole
        repo.save(user)
    }

    @Transactional
    fun deleteUser(targetUserId: Long, actorUsername: String){
        val actor = repo.findByUsername(actorUsername) ?: error("Actor not found")

        if (actor.role != Role.CREATOR) error("Only THE creator can delete")

        val target = repo.findById(targetUserId)
            .orElseThrow { error("User not found") }

        if (target.role == Role.CREATOR) error("THE Creator cannot be deleter")

        repo.delete(target)
    }
}





























