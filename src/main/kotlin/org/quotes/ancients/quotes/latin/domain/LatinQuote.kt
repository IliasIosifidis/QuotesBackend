package org.quotes.ancients.quotes.latin.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "ancient_latin")
class LatinQuote (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name="latin_quote", nullable=false, columnDefinition="TEXT")
    var quote: String,

    @Column(name="translation", columnDefinition="TEXT")
    var translation: String?,

    @Column(name="author", length=255)
    var author: String?
)