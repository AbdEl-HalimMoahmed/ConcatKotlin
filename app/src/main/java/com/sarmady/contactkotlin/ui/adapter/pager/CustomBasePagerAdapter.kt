package com.sarmady.contactkotlin.ui.adapter.pager

import android.app.Activity
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sarmady.contactkotlin.R


abstract class CustomBasePagerAdapter<T>(val activity: Activity, models: List<T> = arrayListOf())
    : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.adapter_card_ad, null)
        container.addView(view)
        return view
    }

    open var models: ArrayList<T> = ArrayList(models)

    fun addModels(models: List<T>) {
        this.models.addAll(models)

        notifyDataSetChanged()
    }

    override fun getCount(): Int = models.size

    fun setModels(models: List<T>) {
        this.models = arrayListOf()
        addModels(models)
    }
}