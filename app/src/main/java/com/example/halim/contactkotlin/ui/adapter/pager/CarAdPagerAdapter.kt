package com.example.halim.contactkotlin.ui.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.halim.contactkotlin.domain.entities.Car
import com.example.halim.contactkotlin.ui.fragment.CarAdCardFragment


class CarAdPagerAdapter(fm: FragmentManager, private val pageWidth: Float = 0.75f, cars: List<Car> = arrayListOf())
    : BasePagerAdapter<Car>(fm, cars) {

    override fun getPageWidth(position: Int): Float = pageWidth

    override fun getItem(position: Int): Fragment = CarAdCardFragment.getInstance(models[position])
}