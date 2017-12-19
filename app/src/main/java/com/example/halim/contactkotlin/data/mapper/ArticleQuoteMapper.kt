package com.example.halim.contactkotlin.data.mapper

import com.example.halim.contactkotlin.data.model.ArticleQuote as QuoteModel
import com.example.halim.contactkotlin.domain.entities.ArticleQuote as QuoteEntity


class ArticleQuoteMapper : Mapper<QuoteModel, QuoteEntity>() {

    override fun transform(from: QuoteModel?): QuoteEntity =
            QuoteEntity(from?.quote)
}