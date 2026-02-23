package org.quotes.ancients.users.api

import org.quotes.ancients.users.service.RegisterRequest
import org.quotes.ancients.users.service.UserService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun register(@RequestBody req: RegisterRequest) = userService.register(req)

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable
                   id: Long,
                   principal: Principal
    ){
        userService.deleteUser(id, principal.name)
    }
}