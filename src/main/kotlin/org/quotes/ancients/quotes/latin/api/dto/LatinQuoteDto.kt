package org.quotes.ancients.quotes.latin.api.dto

data class LatinQuoteDto(
    val id: Long,
    val quote: String,
    val translation: String?,
    val author: String?
)