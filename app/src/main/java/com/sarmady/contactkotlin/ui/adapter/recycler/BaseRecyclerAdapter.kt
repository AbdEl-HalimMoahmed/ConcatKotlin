package com.sarmady.fashiononwheels.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.sarmady.contactkotlin.ui.util.dp2Px


abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T>>(list: List<T>)
    : RecyclerView.Adapter<VH>() {

    open var list: ArrayList<T> = ArrayList(list)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    fun addMore(items: List<T>) {
        val oldSize = list.size
        list.addAll(items)

        notifyItemRangeInserted(oldSize, list.size - oldSize)
    }

    fun resizeViewBasedOnScreenSize(view: View, spanCount: Int, marginDP: Int,
                                    height2WidthRation:Float): View {
        val displayMetrics = view.context?.resources?.displayMetrics
        val layoutParams = view.layoutParams
        val marginPix = view.context?.dp2Px(marginDP.toFloat())?.toInt()
        layoutParams.width = (displayMetrics?.widthPixels ?: 0) / spanCount - (marginPix ?: 0)
        layoutParams.height = (layoutParams.width * height2WidthRation).toInt()
        return view
    }

    override fun getItemCount(): Int = list.size
}