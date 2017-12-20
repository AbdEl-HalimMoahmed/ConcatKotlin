package com.sarmady.contactkotlin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sarmady.contactkotlin.App
import com.sarmady.contactkotlin.R
import com.sarmady.contactkotlin.domain.entities.Article
import com.sarmady.contactkotlin.ui.util.loadUrl
import kotlinx.android.synthetic.main.adapter_article_card.*


class ArticlesSectionItemFragment : Fragment() {

    lateinit var article: Article

    companion object {
        private val BUNDLE_Article_KEY = "article"

        fun getInstance(article: Article): ArticlesSectionItemFragment {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_Article_KEY, article)
            val fragment = ArticlesSectionItemFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        article = arguments?.getParcelable<Article>(BUNDLE_Article_KEY) as Article
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.adapter_article_card, container, false)
                .setLayoutDirection(App.instance.language)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleTitle.text = article.title
        articleImg.loadUrl(article.image?.large)
    }
}