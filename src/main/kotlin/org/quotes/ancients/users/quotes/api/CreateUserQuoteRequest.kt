package org.quotes.ancients.users.quotes.api

data class CreateUserQuoteRequest(
    val title: String,
    val quote: String
)
