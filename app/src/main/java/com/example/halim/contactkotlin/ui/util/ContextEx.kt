package com.example.halim.contactkotlin.ui.util

import android.content.Context
import android.util.TypedValue


fun Context.dp2Px(dp: Float):Float {
    val displayMetrics = resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}