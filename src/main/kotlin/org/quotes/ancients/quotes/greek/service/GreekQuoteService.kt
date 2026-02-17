package org.quotes.ancients.quotes.greek.service

import org.quotes.ancients.quotes.greek.api.GreekQuoteDto
import org.quotes.ancients.quotes.greek.domain.GreekQuote
import org.springframework.stereotype.Service

interface GreekQuoteService {
    fun getAll(): List<GreekQuoteDto>
    fun getRandom(): GreekQuoteDto
}