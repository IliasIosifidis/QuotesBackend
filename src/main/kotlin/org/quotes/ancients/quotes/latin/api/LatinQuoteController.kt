package org.quotes.ancients.quotes.latin.api

import org.quotes.ancients.quotes.latin.api.dto.LatinQuoteDto
import org.quotes.ancients.quotes.latin.service.LatinQuoteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/latins")
class LatinQuoteController(
    private val service: LatinQuoteService
) {
    @GetMapping("/randomQuote")
    fun getRandom(): LatinQuoteDto = service.getRandom()

}