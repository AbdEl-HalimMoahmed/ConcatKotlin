package com.sarmady.contactkotlin.ui.widget

import android.content.Context
import android.graphics.Typeface
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.TypefaceUtils


class CalligraphyDefaultTabLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        TabLayout(context, attrs, defStyleAttr) {

    private var calligraphyTypeface: Typeface? = null

    init {
        initCalligraphyTypeface()
    }

    override fun addTab(tab: TabLayout.Tab, position: Int, setSelected: Boolean) {
        super.addTab(tab, position, setSelected)
        if (calligraphyTypeface != null) {
            val mainView = getChildAt(0) as ViewGroup
            val tabView = mainView.getChildAt(tab.position) as ViewGroup
            val tabChildCount = tabView.childCount
            (0 until tabChildCount)
                    .map { tabView.getChildAt(it) }
                    .filterIsInstance<TextView>()
                    .forEach { it.typeface = calligraphyTypeface }
        }
    }

    private fun initCalligraphyTypeface() {
        val fontPath = CalligraphyConfig.get().fontPath
        if (fontPath != null) {
            calligraphyTypeface = TypefaceUtils.load(resources.assets, fontPath)
        }
    }
}