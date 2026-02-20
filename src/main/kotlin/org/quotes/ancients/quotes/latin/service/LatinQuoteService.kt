package org.quotes.ancients.quotes.latin.service

import org.quotes.ancients.quotes.latin.api.dto.LatinQuoteDto
import org.springframework.stereotype.Service

interface LatinQuoteService {
    fun getRandom(): LatinQuoteDto
}