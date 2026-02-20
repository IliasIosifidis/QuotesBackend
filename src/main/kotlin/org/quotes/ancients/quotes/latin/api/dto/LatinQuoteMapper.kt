package org.quotes.ancients.quotes.latin.api.dto

import org.quotes.ancients.quotes.latin.domain.LatinQuote

fun LatinQuote.toDto(): LatinQuoteDto =
    LatinQuoteDto(
        id = requireNotNull(id) { "LatinQuote.id is null" },
        quote = quote,
        translation = translation,
        author = author
)
