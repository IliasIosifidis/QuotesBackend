package org.quotes.ancients.users.api

import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.quotes.ancients.users.service.UserQuoteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/user-quotes")
class UserQuoteController(
    private val service: UserQuoteService
) {

    @GetMapping
    fun list() = service.list()

    @PostMapping
    fun create(
        principal: Principal,
        @RequestBody req: CreateUserQuoteRequest
    ) = service.create(principal.name, req)
}