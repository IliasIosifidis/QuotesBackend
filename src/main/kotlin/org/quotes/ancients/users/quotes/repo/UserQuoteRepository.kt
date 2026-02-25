package org.quotes.ancients.users.quotes.repo

import org.quotes.ancients.users.quotes.domain.UserQuote
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserQuoteRepository: JpaRepository<UserQuote, Long> {
    fun findAllOrderByCreatedAtDesc(pageable: Pageable): Page<UserQuote>
}