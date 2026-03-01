package org.quotes.ancients.users.quotes.domain

import jakarta.persistence.*

@Entity
@Table(
    name = "user_quote_votes",
    uniqueConstraints = [UniqueConstraint(columnNames = ["username", "quote_id"])]
)
class UserQuoteVote(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, length = 50)
    var username: String,

    @Column(name = "quote_id", nullable = false)
    var quoteId: Long,

    @Column(nullable = false, columnDefinition = "TINYINT")
    var value: Byte
)