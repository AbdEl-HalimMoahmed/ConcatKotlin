package com.example.halim.contactkotlin.ui.activity.home.header

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.support.design.widget.AppBarLayout
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import com.example.halim.contactkotlin.R
import com.example.halim.contactkotlin.ui.animator.RatioAnimator
import com.facebook.rebound.SimpleSpringListener
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringConfig
import com.facebook.rebound.SpringSystem
import kotlinx.android.synthetic.main.widget_home_header.view.*


class StickyHeaderView : FrameLayout {

    @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attributeSet, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attributeSet, defStyleAttr, defStyleRes)

    private lateinit var ratioAnimator: RatioAnimator

    var onStartAnimationEnd: (() -> Unit)? = null
    val toolbar: Toolbar
        get() = toolbarView

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.widget_home_header, this, true)
    }

    private fun setUpListener(onVisible: () -> Unit) {
        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                startInitAnimation {
                    onVisible()
                    onStartAnimationEnd?.invoke()
                }
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
        viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    fun setUpScrollView(scrollView: NestedScrollView) {
        setUpListener {
            setUpAnimation()
            animateWithScrollView(scrollView)
        }
    }

    fun setUpAppBar(appBarLayout: AppBarLayout) {
        setUpListener {
            setUpAnimation()
            animateWithAppBar(appBarLayout)
        }
    }

    fun onStartAnimationEnd(run: () -> Unit) {
        onStartAnimationEnd = run
    }

    private fun setUpAnimation() {

        val actionIconWidth = newCarsBtn.width
        val marginX = (width - (actionIconWidth * 5)) / 5
        val marginY = actionBtnNames.height / 3
        val initMarginX = marginX / 2
        val btns = arrayOf(newCarsBtn, usedCarsBtn, motorcycleBtn, articlesBtn, moreBtn)
        btns.sortBy { it.x }

        ratioAnimator = RatioAnimator.Builder()
                .applyTransition(btns[0], toPoint = Point(initMarginX, marginY))
                .applyTransition(btns[1], toPoint = Point((actionIconWidth + 2 * marginX) - initMarginX, marginY))
                .applyTransition(btns[2], toPoint = Point((2 * actionIconWidth + 3 * marginX) - initMarginX, marginY))
                .applyTransition(btns[3], toPoint = Point((3 * actionIconWidth + 4 * marginX) - initMarginX, marginY))
                .applyTransition(btns[4], toPoint = Point((4 * actionIconWidth + 5 * marginX) - initMarginX, marginY))
                .applyAlpha(actionBtnNames, 0f, 1f, AccelerateInterpolator(4.5f))
                .applyScale(logo, toScaleX = 0f, toScaleY = 0f)
                .applyAlpha(logo, toAlpha = 0f)
                .applyAlpha(imageBG, toAlpha = 0f)
                .applyAlpha(newCarsBtnTV, toAlpha = 0f, interpolator = AccelerateInterpolator(0.02f))
                .applyAlpha(usedCarsBtnTV, toAlpha = 0f, interpolator = AccelerateInterpolator(0.02f))
                .applyAlpha(motorcycleBtnTV, toAlpha = 0f, interpolator = AccelerateInterpolator(0.02f))
                .applyAlpha(articlesBtnTV, toAlpha = 0f, interpolator = AccelerateInterpolator(0.02f))
                .applyAlpha(moreBtnTV, toAlpha = 0f, interpolator = AccelerateInterpolator(0.02f))
                .build()

        actionBtnNames.setPadding(initMarginX, 0, initMarginX, 0)
    }

    private fun setBtnSpringAnimation(btn: View, onFinish: () -> Unit = {}, startNext: () -> Unit) {
        val fromPoint = Point((logo.left + logo.width /4f).toInt(), (logo.top + logo.height /4f).toInt())
        val animator = RatioAnimator.Builder()
                .applyTransition(btn, fromPoint, Point(btn.left, btn.top))
                .build()
        val spring = SpringSystem.create().createSpring()
        spring.springConfig = SpringConfig(90.0, 10.0)
        spring.addListener(object : SimpleSpringListener() {

            var invoked = false
            override fun onSpringUpdate(spring: Spring) {
                if (invoked.not() && spring.currentValue > 0.5) {
                    startNext()
                    invoked = true
                }
                animator.animate(spring.currentValue.toFloat())
            }

            override fun onSpringAtRest(spring: Spring?) {
                onFinish()
            }
        })
        btn.alpha = 1f
        spring.endValue = 1.0
    }

    private fun hideBtns(vararg views: View) {
        for (view in views) {
            view.alpha = 0f
        }
    }

    private fun startInitAnimation(onFinish: () -> Unit) {
        hideBtns(newCarsBtn, usedCarsBtn, motorcycleBtn, articlesBtn, moreBtn)
        setBtnSpringAnimation(newCarsBtn) {
            setBtnSpringAnimation(usedCarsBtn) {
                setBtnSpringAnimation(motorcycleBtn) {
                    setBtnSpringAnimation(articlesBtn) {
                        setBtnSpringAnimation(moreBtn, onFinish = {
                            motorcycleBtn.bringToFront()
                            onFinish()
                        }, startNext = {

                        })
                    }
                }
            }
        }
    }

    private fun animateWithAppBar(appBarLayout: AppBarLayout) {
        appBarLayout.addOnOffsetChangedListener { _, yOffset ->
            val scrollHeight = height - actionBarStub.height
            ratioAnimator.animate(-yOffset / scrollHeight.toFloat(), yOffset.toFloat())
        }
    }

    private fun animateWithScrollView(viewParent: NestedScrollView) {
        viewParent.viewTreeObserver.addOnScrollChangedListener {
            val scrollHeight = height - actionBarStub.height
            translationY = -minOf(viewParent.scrollY, scrollHeight).toFloat()
            ratioAnimator.animate(viewParent.scrollY / scrollHeight.toFloat(), translationY)
        }
    }
}