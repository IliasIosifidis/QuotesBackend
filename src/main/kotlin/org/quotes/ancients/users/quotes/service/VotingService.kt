package org.quotes.ancients.users.quotes.service

import jakarta.transaction.Transactional
import org.quotes.ancients.users.quotes.domain.UserQuote
import org.quotes.ancients.users.quotes.domain.UserQuoteVote
import org.quotes.ancients.users.quotes.repo.UserQuoteRepository
import org.quotes.ancients.users.quotes.repo.UserQuoteVoteRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class VotingService(
    private val quoteRepo: UserQuoteRepository,
    private val voteRepo: UserQuoteVoteRepository
) {
    @Transactional
    fun vote(username: String, quoteId: Long, value: Int): UserQuote {
        require(value == 1 || value == -1) // Like or dislike

        val quote = quoteRepo.findById(quoteId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Quote not found") }

        val existing = voteRepo.findByUsernameAndQuoteId(username, quoteId)

        val delta = when {
            existing == null -> {
                voteRepo.save(UserQuoteVote(username = username, quoteId = quoteId, value =  value))
                value
            }
            existing.value == value -> {
                voteRepo.delete(existing)
                -value
            }
            else -> {
                existing.value = value
                voteRepo.save(existing)
                2* value
            }
        }
        quote.score += delta
        return quoteRepo.save(quote)
    }

}