package org.quotes.ancients.users.users.api.dto

import org.quotes.ancients.users.users.domain.AppUser

data class UserResponseDto(
    val id: Long,
    val username: String,
    val email: String,
    val role: String
)

fun AppUser.toDto() = UserResponseDto(
    id = this.id,
    username = this.username,
    email = this.email,
    role = this.role.name
)