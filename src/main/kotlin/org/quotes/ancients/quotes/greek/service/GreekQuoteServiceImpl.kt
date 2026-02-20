package org.quotes.ancients.quotes.greek.service

import org.quotes.ancients.quotes.greek.api.dto.GreekQuoteDto
import org.quotes.ancients.quotes.greek.api.dto.toDto
import org.quotes.ancients.quotes.greek.repo.GreekQuotesRepository
import org.springframework.stereotype.Service

@Service
class GreekQuoteServiceImpl(
    private val repo: GreekQuotesRepository
) : GreekQuoteService {
    override fun getAll(): List<GreekQuoteDto> = repo.findAll().map { it.toDto() }

    override fun getRandom(): GreekQuoteDto {
        val entity = repo.getRandom()
            ?: throw NoSuchElementException("No quotes found in the database")
        return entity.toDto()
    }
}