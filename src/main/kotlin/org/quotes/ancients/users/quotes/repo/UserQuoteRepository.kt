package org.quotes.ancients.users.quotes.repo

import org.quotes.ancients.users.quotes.domain.UserQuote
import org.springframework.data.jpa.repository.JpaRepository

interface UserQuoteRepository: JpaRepository<UserQuote, Long> {
}