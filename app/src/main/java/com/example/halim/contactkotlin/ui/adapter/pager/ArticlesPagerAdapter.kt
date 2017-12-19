package com.example.halim.contactkotlin.ui.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.halim.contactkotlin.domain.entities.Article
import com.example.halim.contactkotlin.ui.fragment.ArticlesSectionItemFragment


class ArticlesPagerAdapter(fm: FragmentManager, articles: List<Article> = arrayListOf())
    : BasePagerAdapter<Article>(fm, articles) {

    override fun getItem(position: Int): Fragment = ArticlesSectionItemFragment.getInstance(models[position])
}