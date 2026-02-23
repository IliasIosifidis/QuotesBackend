package org.quotes.ancients.users.repo

import org.quotes.ancients.users.domain.UserQuote
import org.springframework.data.jpa.repository.JpaRepository

interface UserQuoteRepository: JpaRepository<UserQuote, Long> {
}
