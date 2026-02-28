package org.quotes.ancients.users.quotes.api

import org.quotes.ancients.users.quotes.domain.UserQuote
import org.quotes.ancients.users.quotes.service.UserQuoteService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/user-quotes")
class UserQuoteController(
    private val service: UserQuoteService
) {

    @GetMapping
    fun list(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): Page<UserQuote> = service.list(page,size)

    @PostMapping
    fun create(
        principal: Principal,
        @RequestBody req: CreateUserQuoteRequest
    ) = service.create(principal.name, req)

    @DeleteMapping("/{quoteId}")
    fun delete(
        @PathVariable quoteId: Long
    ) = service.deleteQuote(quoteId)
}