package com.example.halim.contactkotlin.data.mapper


import com.example.halim.contactkotlin.data.model.Article as ArticleModel
import com.example.halim.contactkotlin.domain.entities.Article as ArticleEntity
import com.example.halim.contactkotlin.data.model.ArticleImage as ArticleImageModel
import com.example.halim.contactkotlin.domain.entities.Image as ArticleImageEntity
import com.example.halim.contactkotlin.data.model.ArticleAuthor as AuthorModel
import com.example.halim.contactkotlin.domain.entities.ArticleAuthor as AuthorEntity
import com.example.halim.contactkotlin.data.model.ArticleGallery as GalleryModel
import com.example.halim.contactkotlin.domain.entities.ArticleGallery as GalleryEntity
import com.example.halim.contactkotlin.data.model.ArticleVideo as VideoModel
import com.example.halim.contactkotlin.domain.entities.ArticleVideo as VideoEntity
import com.example.halim.contactkotlin.data.model.ArticleQuote as QuoteModel
import com.example.halim.contactkotlin.domain.entities.ArticleQuote as QuoteEntity

class ArticleMapper(private val imageMapper: Mapper<ArticleImageModel, ArticleImageEntity>,
                    private val articleAuthorMapper: Mapper<AuthorModel, AuthorEntity>,
                    private val galleryMapper: Mapper<GalleryModel, GalleryEntity>,
                    private val videoMapper: Mapper<VideoModel, VideoEntity>,
                    private val quoteMapper: Mapper<QuoteModel, QuoteEntity>) : Mapper<ArticleModel, ArticleEntity>() {

    override fun transform(from: ArticleModel?): ArticleEntity =
            ArticleEntity(from?.id ?: -1L, from?.date, from?.title, from?.body, imageMapper.transform(from?.articleImage),
                    articleAuthorMapper.transform(from?.writer), from?.status ?: -1, imageMapper.transform(from?.bodyImages),
                    from?.articleType, galleryMapper.transform(from?.bodyGalleries), videoMapper.transform(from?.bodyVideos),
                    quoteMapper.transform(from?.quotes))
}