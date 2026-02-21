package org.quotes.ancients.quotes.greek.api.dto

import org.quotes.ancients.quotes.greek.domain.GreekQuote

fun GreekQuote.toDto(): GreekQuoteDto = 
    GreekQuoteDto(
        id = requireNotNull(id) { "GreekQuote.id is null (entity not persisted?)" },
        quote = quote,
    )