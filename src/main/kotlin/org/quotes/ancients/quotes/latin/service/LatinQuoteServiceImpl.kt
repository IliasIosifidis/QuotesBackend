package org.quotes.ancients.quotes.latin.service

import org.quotes.ancients.quotes.latin.api.dto.LatinQuoteDto
import org.quotes.ancients.quotes.latin.api.dto.toDto
import org.quotes.ancients.quotes.latin.repo.LatinQuoteRepository
import org.springframework.stereotype.Service

@Service
class LatinQuoteServiceImpl(
    private val repo: LatinQuoteRepository
): LatinQuoteService {
    override fun getRandom(): LatinQuoteDto {
     val entity = repo.getRandom() ?: throw NoSuchElementException("No quotes found")
        return entity.toDto()
    }
}