package org.quotes.ancients.users.domain

import jakarta.persistence.*
import org.quotes.ancients.users.domain.Role
import java.time.Instant

@Entity
@Table(
    name = "app_users",
    indexes = [
        Index(name = "ix_app_users_username", columnList = "username", unique = true),
        Index(name = "ix_app_users_email", columnList = "email", unique = true)
    ])
class AppUser (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, unique = true, length = 50)
    var username: String,

    @Column(nullable = false, unique = true, length = 120)
    var email: String,

    @Column(nullable = false, length = 255)
    var passwordHash: String?,

    @Enumerated(EnumType.STRING)
    @Column
    var role: Role = Role.USER,

    @Column(nullable = false)
    var enabled: Boolean = true,

    @Column(nullable = false, updatable = false)
    var createdAt: Instant = Instant.now(),

    @Column(nullable = false)
    var updatedAt: Instant = Instant.now()
){
    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }
}