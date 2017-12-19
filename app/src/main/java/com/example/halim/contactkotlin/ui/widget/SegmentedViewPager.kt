package com.example.halim.contactkotlin.ui.widget

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.example.halim.contactkotlin.ui.adapter.pager.BasePagerAdapter
import java.util.*


class SegmentedViewPager (val viewPager: ViewPager, var tabLayout: TabLayout? = null) {

    val tabs2PagesMap = ArrayList<Int>(3)

    init {

        tabLayout?.visibility = View.GONE
        tabLayout?.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout?.tabMode = TabLayout.MODE_FIXED

        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            var prevTabPos = 0

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                prevTabPos = tab?.position ?: 0
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = when {
                    prevTabPos > tab.position && tab.isPagerSelected -> {
                        tabs2PagesMap[tab.position] - 1
                    }
                    tab.position - 1 >= 0 -> tabs2PagesMap[tab.position - 1]
                    else -> 0
                }

                tabLayout?.setPagerSelected(false)
            }
        })

        viewPager.overScrollMode = View.OVER_SCROLL_NEVER
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val tabIndex = tabs2PagesMap.indexOfFirst { position < it }
                tabLayout?.setSelected(tabIndex)
            }
        })
    }

    fun <T> addSegment(list: List<T>, title:Int) {
        addSegment(list, viewPager.context.getString(title))
    }

    fun <T> addSegment(list: List<T>, title:String? = null) {
        val last = if (tabs2PagesMap.isEmpty()) 0 else tabs2PagesMap.last()
        tabs2PagesMap.add(last + list.size)
        if (title != null) {
            tabLayout?.visibility = View.VISIBLE
            tabLayout?.addTab(title)
        }
        (viewPager.adapter as BasePagerAdapter<T>).addModels(list)
    }

    fun <T> setAdapter(adapter: BasePagerAdapter<T>) {
        viewPager.adapter = adapter
    }

    fun TabLayout.setSelected(index: Int) {
        getTabAt(index)?.isPagerSelected = true
        getTabAt(index)?.select()
    }

    private fun TabLayout.setPagerSelected(value: Boolean) {
        for (i in 0..tabCount)
            getTabAt(i)?.isPagerSelected = value
    }

    private var TabLayout.Tab.isPagerSelected: Boolean
        get() = tag as Boolean
        set(value) {
            tag = value
        }

    private fun TabLayout.addTab(title: String) {
        val tab = newTab().setText(title)
        addTab(tab)
    }
}