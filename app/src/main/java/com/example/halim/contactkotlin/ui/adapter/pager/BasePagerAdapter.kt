package com.example.halim.contactkotlin.ui.adapter.pager

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


abstract class BasePagerAdapter<T>(fm: FragmentManager, models: List<T> = arrayListOf())
    : FragmentPagerAdapter(fm) {

    open var models: ArrayList<T> = ArrayList(models)

    fun addModels(models: List<T>) {
        this.models.addAll(models)

        notifyDataSetChanged()
    }

    override fun getItemId(position: Int) = (position + hashCode()).toLong()

    override fun getCount(): Int = models.size

    fun setModels(models: List<T>) {
        this.models = arrayListOf()
        addModels(models)
    }
}