package org.quotes.ancients.users.quotes.service

import jakarta.transaction.Transactional
import org.quotes.ancients.users.quotes.api.CreateUserQuoteRequest
import org.quotes.ancients.users.quotes.domain.UserQuote
import org.quotes.ancients.users.quotes.repo.UserQuoteRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserQuoteService(
    private val repo: UserQuoteRepository
) {
    @Transactional
    fun create(username: String, req: CreateUserQuoteRequest): UserQuote {
        val entity = UserQuote(
            id = 0,
            username = username,
            title = req.title.trim(),
            quote = req.quote.trim(),
            score = 0,
        )
        return repo.save(entity)
    }

    fun list(page: Int, size: Int): Page<UserQuote> {
        val safeSize = size.coerceIn(1,50)
        val safePage = page.coerceAtLeast(0)
        return repo.findAllByOrderByCreatedAtDesc(PageRequest.of(safePage, safeSize))
    }

    @Transactional
    fun deleteQuote(quoteId: Long){
        val quote = repo.findById(quoteId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Quote Not Found") }

        repo.delete(quote)
    }
}