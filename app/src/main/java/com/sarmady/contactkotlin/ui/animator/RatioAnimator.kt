package com.sarmady.contactkotlin.ui.animator

import android.animation.ValueAnimator
import android.graphics.Point
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator


class RatioAnimator(private val animatorBundles: List<AnimatorBundle>) {

    companion object {
        val DEFAULT_SCALE = -12345f
        val DEFAULT_POINT = Point(-12345, -12345)
    }

    fun animate(ratio: Float, parentTranslateY: Float = 0f) {

        for ((view, from, delta, interpolator, animationType) in animatorBundles) {
            val animationValue = interpolator.getInterpolation(ratio) * delta + from

            when (animationType) {
                is TranslateX -> view.translationX = animationValue
                is TranslateY -> view.translationY = animationValue - parentTranslateY
                is ScaleX -> view.scaleX = animationValue
                is ScaleY -> view.scaleY = animationValue
                is Alpha -> view.alpha = animationValue
            }
        }
    }

    fun play(duration: Long, interpolator: Interpolator) {

        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.interpolator = interpolator
        animator.duration = duration
        animator.addUpdateListener {
            animate(it.animatedValue as Float)
        }
        animator.start()
    }

    class Builder {
        private val animatorBundles = arrayListOf<AnimatorBundle>()

        fun applyTransition(view: View, fromPoint: Point = DEFAULT_POINT, toPoint: Point,
                            interpolator: Interpolator = LinearInterpolator()): Builder {

            val from = if (fromPoint == DEFAULT_POINT) Point(view.left, view.top) else fromPoint

            animatorBundles.add(AnimatorBundle(view, view.translationX + (from.x - view.x), (toPoint.x - from.x) - view.translationX,
                    interpolator, TranslateX()))
            view.translationX += from.x - view.x

            animatorBundles.add(AnimatorBundle(view, view.translationY + (from.y - view.y), (toPoint.y - from.y) - view.translationY,
                    interpolator, TranslateY()))
            view.translationY += from.y - view.y

            return this
        }

        fun applyScale(view: View, fromScaleX: Float = DEFAULT_SCALE, fromScaleY: Float = DEFAULT_SCALE, toScaleX: Float, toScaleY: Float,
                       interpolator: Interpolator = LinearInterpolator()): Builder {

            val startX = if (fromScaleX == DEFAULT_SCALE) view.scaleX else fromScaleX
            val startY = if (fromScaleY == DEFAULT_SCALE) view.scaleY else fromScaleY

            animatorBundles.add(AnimatorBundle(view, startX, toScaleX - startX, interpolator, ScaleX()))

            animatorBundles.add(AnimatorBundle(view, startY, toScaleY - startY, interpolator, ScaleY()))

            return this
        }

        fun applyAlpha(view: View, fromAlpha: Float = 1f, toAlpha: Float,
                       interpolator: Interpolator = LinearInterpolator()): Builder {

            view.alpha = fromAlpha

            animatorBundles.add(AnimatorBundle(view, view.alpha, toAlpha - view.alpha, interpolator, Alpha()))

            return this
        }

        fun build() = RatioAnimator(animatorBundles)
    }
}