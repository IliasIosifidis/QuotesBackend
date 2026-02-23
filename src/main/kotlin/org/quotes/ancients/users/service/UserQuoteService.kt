package org.quotes.ancients.users.service

import jakarta.transaction.Transactional
import org.quotes.ancients.users.api.CreateUserQuoteRequest
import org.quotes.ancients.users.domain.UserQuote
import org.quotes.ancients.users.repo.UserQuoteRepository
import org.springframework.stereotype.Service

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

        try {
            return repo.save(entity)
        } catch (e: Exception){
            throw IllegalStateException("You can only post once every 6 hours")
        }
    }

    fun list() = repo.findAll()
}
