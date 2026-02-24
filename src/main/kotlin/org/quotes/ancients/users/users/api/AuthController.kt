package org.quotes.ancients.users.users.api

import org.quotes.ancients.comon.util.JwtService
import org.quotes.ancients.users.users.service.RegisterRequest
import org.quotes.ancients.users.users.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService,
    private val authManager: AuthenticationManager,
    private val jwtService: JwtService
) {
    @PostMapping("/register")
    fun register(@RequestBody req: RegisterRequest) = userService.register(req)

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): LoginResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(req.username, req.password)
        )
        return LoginResponse(jwtService.issue(req.username))
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable
                   id: Long,
                   principal: Principal
    ){
        userService.deleteUser(id, principal.name)
    }

    data class LoginResponse(val token: String)
    data class LoginRequest(val username: String, val password: String)

}