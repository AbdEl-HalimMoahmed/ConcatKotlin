package com.sarmady.contactkotlin.ui.animator

import android.view.View
import android.view.animation.Interpolator


data class AnimatorBundle(val view: View,
                          val from: Float,
                          val delta: Float,
                          val interpolator: Interpolator,
                          val animationType: AnimationType)