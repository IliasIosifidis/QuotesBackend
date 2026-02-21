package org.quotes.ancients.quotes.chinese.api.dto

import org.quotes.ancients.quotes.chinese.domain.ChineseQuote

fun ChineseQuote.toDto(): ChineseQuoteDto =
    ChineseQuoteDto(
        id = requireNotNull(id) { "Id not found in the database" },
        quoteEn = requireNotNull(quoteEn) { "English translation not found in the database" },
        chineseLine = requireNotNull(chineseLine) { "Chinese Original not found in the database" },
        author = author,
        explanation = explanation
    )