package org.quotes.ancients.quotes.greek.repo

import org.quotes.ancients.quotes.greek.api.dto.GreekQuoteDto
import org.quotes.ancients.quotes.greek.domain.GreekQuote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GreekQuotesRepository : JpaRepository<GreekQuote, Long>{
    @Query(value = "select * from ancient_greeks order by rand() limit 1", nativeQuery = true)
    fun getRandom(): GreekQuote?
}