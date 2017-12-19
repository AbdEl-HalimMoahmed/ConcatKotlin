package com.example.halim.contactkotlin.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.halim.contactkotlin.R
import com.example.halim.contactkotlin.domain.entities.Article
import com.example.halim.contactkotlin.ui.util.loadUrl
import com.halim.fashiononwheels.ui.adapter.BaseRecyclerAdapter
import com.halim.fashiononwheels.ui.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.adapter_article_home.view.*


class HomeArticlesAdapter(articles: List<Article>)
    : BaseRecyclerAdapter<Article, HomeArticlesAdapter.ArticleVH>(articles) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleVH
            = ArticleVH(LayoutInflater.from(parent?.context)
            .inflate(R.layout.adapter_article_home, parent, false))

    class ArticleVH(view: View) : BaseViewHolder<Article>(view) {

        override fun bind(model: Article) {
            itemView?.articleTitle?.text = model.title
            itemView?.articleDate?.text = model.date
            itemView?.articleImage?.loadUrl(model.image?.small)
        }
    }
}