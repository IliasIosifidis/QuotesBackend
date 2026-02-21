package org.quotes.ancients.quotes.chinese.domain

import jakarta.persistence.*

@Entity
@Table(
    name = "chinese_quotes",
    indexes = [Index(name = "idx_quote_hash", columnList = "quote_hash", unique = true)]
)
class ChineseQuote(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "quote_en", nullable = false, columnDefinition = "TEXT")
    val quoteEn: String? = null,

    @Column(name = "chinese_line", nullable = false, columnDefinition = "TEXT")
    val chineseLine: String? = null,

    @Column(name = "author")
    val author: String? = null,

    @Column(name = "explanation", columnDefinition = "TEXT")
    val explanation: String? = null,

    @Column(name = "source_url", nullable = false, length = 512)
    val sourceUrl: String? = null,

    @Column(name = "quote_hash", nullable = false, length = 64)
    val quoteHash: String? = null
)