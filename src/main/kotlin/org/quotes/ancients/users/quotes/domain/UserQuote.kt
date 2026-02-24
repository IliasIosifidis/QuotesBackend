package org.quotes.ancients.users.quotes.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(
    name = "user_quotes",
    indexes = [
        Index(name = "ix_user_quotes_username", columnList = "username"),
        Index(name = "ix_user_quotes_score", columnList = "score")
    ])
class UserQuote(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, length = 50)
    var username: String,

    @Column(nullable = false, length = 50)
    var title: String,

    @Column(nullable = false, length = 400)
    var quote: String,

    @Column(nullable = false)
    var score: Int = 0,

    @Column(nullable = false, updatable = false)
    var createdAt: Instant = Instant.now(),

    @Column(nullable = false)
    var updatedAt : Instant = Instant.now()
){
    @PreUpdate
    fun onUpdate() { updatedAt = Instant.now() }
}