package com.example.halim.contactkotlin.ui.adapter.pager.indicator

import android.support.v4.view.ViewPager

interface PageIndicator : ViewPager.OnPageChangeListener {

    fun setViewPager(view: ViewPager)

    fun setViewPager(view: ViewPager, initialPosition: Int)

    fun setCurrentItem(item: Int)

    fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener)

    fun notifyDataSetChanged()
}
