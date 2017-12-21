package com.sarmady.contactkotlin.data.mapper


import com.sarmady.contactkotlin.di.data.mapper.MapperComponent
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import com.sarmady.contactkotlin.data.model.ArticleAuthor as AuthorModel
import com.sarmady.contactkotlin.domain.entities.ArticleAuthor as AuthorEntity

class ArticleAuthorMapperTest : MapperTestContract<AuthorModel, AuthorEntity>() {

    private val authorName = "Name"

    @BeforeAll
    fun init() {

    }

//    override fun injectMapper(component: MapperComponent) = component.getAuthorMapper()

    override fun getFromValidValue(): AuthorModel = AuthorModel(authorName)

    override fun getToValidValue(): AuthorEntity = AuthorEntity(authorName)

    // TODO handel this case
    override fun getFromInvalidValue(): AuthorModel = AuthorModel(null)

    @Test
    fun test() {
        validFromTransformToValidTo()
    }
}