package com.example.halim.contactkotlin.di.module.app

import com.example.halim.contactkotlin.data.dataset.ArticleDataSet
import com.example.halim.contactkotlin.data.dataset.VehicleDataSet
import com.example.halim.contactkotlin.data.mapper.*
import com.example.halim.contactkotlin.data.model.VehicleImage
import com.example.halim.contactkotlin.data.repository.ArticleRepositoryImp
import com.example.halim.contactkotlin.data.repository.VehicleRepositoryImp
import com.example.halim.contactkotlin.domain.repository.ArticleRepository
import com.example.halim.contactkotlin.domain.repository.VehiclesRepository
import com.halim.fashiononwheels.di.scope.AppScope
import dagger.Module
import dagger.Provides
import com.example.halim.contactkotlin.data.model.Article as ArticleModel
import com.example.halim.contactkotlin.data.model.ArticleAuthor as AuthorModel
import com.example.halim.contactkotlin.data.model.ArticleGallery as GalleryModel
import com.example.halim.contactkotlin.data.model.ArticleImage as ArticleImageModel
import com.example.halim.contactkotlin.data.model.ArticleQuote as QuoteModel
import com.example.halim.contactkotlin.data.model.ArticleVideo as VideoModel
import com.example.halim.contactkotlin.data.model.NewCar as NewCarModel
import com.example.halim.contactkotlin.data.model.UsedCar as UsedCarModel
import com.example.halim.contactkotlin.domain.entities.Article as ArticleEntity
import com.example.halim.contactkotlin.domain.entities.ArticleAuthor as AuthorEntity
import com.example.halim.contactkotlin.domain.entities.ArticleGallery as GalleryEntity
import com.example.halim.contactkotlin.domain.entities.ArticleQuote as QuoteEntity
import com.example.halim.contactkotlin.domain.entities.ArticleVideo as VideoEntity
import com.example.halim.contactkotlin.domain.entities.Image as ImageEntity
import com.example.halim.contactkotlin.domain.entities.NewCar as NewCarEntity
import com.example.halim.contactkotlin.domain.entities.UsedCar as UsedCarEntity

@Module
class RepositoryModule {

    @Provides
    @AppScope
    fun provideArticleImageMapper(): Mapper<ArticleImageModel, ImageEntity> = ArticleImageMapper()

    @Provides
    @AppScope
    fun provideArticleAuthorMapper(): Mapper<AuthorModel, AuthorEntity> = ArticleAuthorMapper()

    @Provides
    @AppScope
    fun provideArticleGalleryMapper(imageMapper: Mapper<ArticleImageModel, ImageEntity>): Mapper<GalleryModel, GalleryEntity> =
            ArticleGalleryMapper(imageMapper)

    @Provides
    @AppScope
    fun provideArticleVideoMapper(imageMapper: Mapper<ArticleImageModel, ImageEntity>): Mapper<VideoModel, VideoEntity> =
            ArticleVideoMapper(imageMapper)

    @Provides
    @AppScope
    fun provideArticleQuoteMapper(): Mapper<QuoteModel, QuoteEntity> = ArticleQuoteMapper()

    @Provides
    @AppScope
    fun provideArticleMapper(imageMapper: Mapper<ArticleImageModel, ImageEntity>,
                             articleAuthorMapper: Mapper<AuthorModel, AuthorEntity>,
                             galleryMapper: Mapper<GalleryModel, GalleryEntity>,
                             videoMapper: Mapper<VideoModel, VideoEntity>,
                             quoteMapper: Mapper<QuoteModel, QuoteEntity>): Mapper<ArticleModel, ArticleEntity> =
            ArticleMapper(imageMapper, articleAuthorMapper, galleryMapper, videoMapper, quoteMapper)

    @Provides
    @AppScope
    fun provideArticleRepository(articleDataSet: ArticleDataSet, mapper: Mapper<ArticleModel, ArticleEntity>): ArticleRepository
            = ArticleRepositoryImp(articleDataSet, mapper)

    @Provides
    @AppScope
    fun provideVehicleImageMapper(): Mapper<VehicleImage, ImageEntity> = VechileIMageMapper()

    @Provides
    @AppScope
    fun provideNewCarMapper(imageMapper: Mapper<VehicleImage, ImageEntity>): Mapper<NewCarModel, NewCarEntity> = NewCarMapper(imageMapper)

    @Provides
    @AppScope
    fun provideUsedCarMapper(imageMapper: Mapper<VehicleImage, ImageEntity>): Mapper<UsedCarModel, UsedCarEntity> = UsedCarMapper(imageMapper)

    @Provides
    @AppScope
    fun provideVehicleRepository(vehicleDataSet: VehicleDataSet, newCarMapper: Mapper<NewCarModel, NewCarEntity>,
                                 usedCarMapper: Mapper<UsedCarModel, UsedCarEntity>): VehiclesRepository
            = VehicleRepositoryImp(vehicleDataSet, newCarMapper, usedCarMapper)
}