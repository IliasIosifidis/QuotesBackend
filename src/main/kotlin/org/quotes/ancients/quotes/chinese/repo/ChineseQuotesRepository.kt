package org.quotes.ancients.quotes.chinese.repo

import org.quotes.ancients.quotes.chinese.domain.ChineseQuote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ChineseQuotesRepository : JpaRepository<ChineseQuote, Long> {
    @Query(value = "select * from chinese_quotes order by rand() limit 1", nativeQuery = true)
    fun getRandomQuote(): ChineseQuote?
}