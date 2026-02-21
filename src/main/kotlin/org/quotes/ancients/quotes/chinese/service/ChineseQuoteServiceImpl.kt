package org.quotes.ancients.quotes.chinese.service

import org.quotes.ancients.quotes.chinese.api.dto.ChineseQuoteDto
import org.quotes.ancients.quotes.chinese.api.dto.toDto
import org.quotes.ancients.quotes.chinese.repo.ChineseQuotesRepository
import org.springframework.stereotype.Service

@Service
class ChineseQuoteServiceImpl(
    private val repo: ChineseQuotesRepository
): ChineseQuoteService {
    override fun getRandomQuote(): ChineseQuoteDto {
        val entity = repo.getRandomQuote()
            ?: throw NoSuchElementException("No quotes in the database")
        return entity.toDto()
    }
}