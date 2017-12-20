package com.sarmady.fashiononwheels.ui.adapter.decorator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sarmady.contactkotlin.ui.util.dp2Px

class DividerItemDecorator(context: Context, rightMargin: Int = 0, leftMargin: Int = 0)
    : RecyclerView.ItemDecoration() {

    private val divider: Drawable?
    private val rightMargin: Int
    private val leftMargin: Int

    init {
        val typedArray = context.obtainStyledAttributes(ATTRS)
        divider = typedArray.getDrawable(0)
        typedArray.recycle()
        this.rightMargin = context.dp2Px(rightMargin.toFloat()).toInt()
        this.leftMargin = context.dp2Px(leftMargin.toFloat()).toInt()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)

        val left = parent.paddingLeft + leftMargin
        val right = parent.width - parent.paddingRight - rightMargin

        val childCount = parent.childCount - 1
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin + child.translationY.toInt()
            val bottom = top + (divider?.intrinsicHeight ?: 0)
            divider?.alpha = (child.alpha * 255).toInt()
            divider?.setBounds(left, top, right, bottom)
            divider?.draw(c)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.set(0, 0, 0, divider?.intrinsicHeight ?: 0)
    }

    companion object {
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}
