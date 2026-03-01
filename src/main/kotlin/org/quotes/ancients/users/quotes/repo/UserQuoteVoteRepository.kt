package org.quotes.ancients.users.quotes.repo

import org.quotes.ancients.users.quotes.domain.UserQuoteVote
import org.springframework.data.jpa.repository.JpaRepository

interface UserQuoteVoteRepository: JpaRepository<UserQuoteVote, Long> {
    fun findByUsernameAndQuoteId(username: String, quoteId: Long): UserQuoteVote?
}