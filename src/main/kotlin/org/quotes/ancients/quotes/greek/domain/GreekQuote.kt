package org.quotes.ancients.quotes.greek.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "ancient_greeks")
class GreekQuote (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, columnDefinition = "text")
    var quote: String,

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    var createdAt: Instant? = null
)
