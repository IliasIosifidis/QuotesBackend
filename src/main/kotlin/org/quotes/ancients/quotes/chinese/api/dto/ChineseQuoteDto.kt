package org.quotes.ancients.quotes.chinese.api.dto

data class ChineseQuoteDto(
    val id: Long,
    val quoteEn: String,
    val chineseLine: String,
    val author: String?,
    val explanation: String?
)