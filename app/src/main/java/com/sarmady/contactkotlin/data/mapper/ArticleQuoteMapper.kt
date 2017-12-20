package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.data.model.ArticleQuote as QuoteModel
import com.sarmady.contactkotlin.domain.entities.ArticleQuote as QuoteEntity


class ArticleQuoteMapper : Mapper<QuoteModel, QuoteEntity>() {

    override fun transform(from: QuoteModel?) = from?.let { QuoteEntity(from.quote) }

}