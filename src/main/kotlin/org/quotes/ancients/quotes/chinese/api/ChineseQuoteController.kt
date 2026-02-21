package org.quotes.ancients.quotes.chinese.api

import org.quotes.ancients.quotes.chinese.api.dto.ChineseQuoteDto
import org.quotes.ancients.quotes.chinese.service.ChineseQuoteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chinese")
class ChineseQuoteController(
    private val service: ChineseQuoteService
) {
    @GetMapping("/random")
    fun getRandomQuote(): ChineseQuoteDto? = service.getRandomQuote()
}