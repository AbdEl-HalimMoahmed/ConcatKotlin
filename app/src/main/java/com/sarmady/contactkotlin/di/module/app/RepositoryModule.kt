package com.sarmady.contactkotlin.di.module.app

import com.sarmady.contactkotlin.data.dataset.ArticleDataSet
import com.sarmady.contactkotlin.data.dataset.VehicleDataSet
import com.sarmady.contactkotlin.data.mapper.*
import com.sarmady.contactkotlin.data.model.VehicleImage
import com.sarmady.contactkotlin.data.repository.ArticleRepositoryImp
import com.sarmady.contactkotlin.data.repository.VehicleRepositoryImp
import com.sarmady.contactkotlin.domain.repository.ArticleRepository
import com.sarmady.contactkotlin.domain.repository.VehiclesRepository
import com.sarmady.fashiononwheels.di.scope.AppScope
import dagger.Module
import dagger.Provides
import com.sarmady.contactkotlin.data.model.Article as ArticleModel
import com.sarmady.contactkotlin.data.model.ArticleAuthor as AuthorModel
import com.sarmady.contactkotlin.data.model.ArticleGallery as GalleryModel
import com.sarmady.contactkotlin.data.model.ArticleImage as ArticleImageModel
import com.sarmady.contactkotlin.data.model.ArticleQuote as QuoteModel
import com.sarmady.contactkotlin.data.model.ArticleVideo as VideoModel
import com.sarmady.contactkotlin.data.model.NewCar as NewCarModel
import com.sarmady.contactkotlin.data.model.UsedCar as UsedCarModel
import com.sarmady.contactkotlin.domain.entities.Article as ArticleEntity
import com.sarmady.contactkotlin.domain.entities.ArticleAuthor as AuthorEntity
import com.sarmady.contactkotlin.domain.entities.ArticleGallery as GalleryEntity
import com.sarmady.contactkotlin.domain.entities.ArticleQuote as QuoteEntity
import com.sarmady.contactkotlin.domain.entities.ArticleVideo as VideoEntity
import com.sarmady.contactkotlin.domain.entities.Image as ImageEntity
import com.sarmady.contactkotlin.domain.entities.NewCar as NewCarEntity
import com.sarmady.contactkotlin.domain.entities.UsedCar as UsedCarEntity

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