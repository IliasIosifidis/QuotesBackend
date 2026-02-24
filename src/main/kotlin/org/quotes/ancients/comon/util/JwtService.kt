package org.quotes.ancients.comon.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.sql.Date
import java.time.Instant

@Service
class JwtService {
    private val key = Keys.hmacShaKeyFor(
        (System.getenv("JWT_SECRET") ?: error("JWT_SECRET missing"))
            .toByteArray(StandardCharsets.UTF_8)
    )

    fun issue(username: String): String {
        val now = Instant.now()
        val expiration = now.plusSeconds(60 * 60 * 24 * 7) // a week time limit of the JWT

        return Jwts.builder()
            .subject(username)
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiration))
            .signWith(key)
            .compact()
    }

    fun parseUsername(token: String): String? {
        return try {
            val claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload
            claims.subject
        } catch (e: Exception){
            println("JWT parse failed: ${e.javaClass.simpleName}: ${e.message}")
            null
        }
    }
}