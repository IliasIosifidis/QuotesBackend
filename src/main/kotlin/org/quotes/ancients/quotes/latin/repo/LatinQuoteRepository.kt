package org.quotes.ancients.quotes.latin.repo

import org.quotes.ancients.quotes.latin.domain.LatinQuote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LatinQuoteRepository: JpaRepository<LatinQuote, Long> {
    @Query("select * from ancient_latin order by rand() limit 1", nativeQuery = true)
    fun getRandom(): LatinQuote?
}