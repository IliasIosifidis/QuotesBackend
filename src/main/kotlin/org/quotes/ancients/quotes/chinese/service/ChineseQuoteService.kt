package org.quotes.ancients.quotes.chinese.service

import org.quotes.ancients.quotes.chinese.api.dto.ChineseQuoteDto

interface ChineseQuoteService {
    fun getRandomQuote(): ChineseQuoteDto
}