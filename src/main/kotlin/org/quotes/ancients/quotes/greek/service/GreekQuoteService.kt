package org.quotes.ancients.quotes.greek.service

import org.quotes.ancients.quotes.greek.api.dto.GreekQuoteDto

interface GreekQuoteService {
    fun getAll(): List<GreekQuoteDto>
    fun getRandom(): GreekQuoteDto
}