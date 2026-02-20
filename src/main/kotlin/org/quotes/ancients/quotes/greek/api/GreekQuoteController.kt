package org.quotes.ancients.quotes.greek.api

import org.quotes.ancients.quotes.greek.api.dto.GreekQuoteDto
import org.quotes.ancients.quotes.greek.service.GreekQuoteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/greeks")
class GreekQuoteController(
    private val service: GreekQuoteService
) {
    @GetMapping
    fun getAll(): List<GreekQuoteDto> = service.getAll()

    @GetMapping("/random")
    fun getRandom(): GreekQuoteDto? = service.getRandom()
}