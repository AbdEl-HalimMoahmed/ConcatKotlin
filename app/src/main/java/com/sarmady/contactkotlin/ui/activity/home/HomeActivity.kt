package com.sarmady.contactkotlin.ui.activity.home

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.afollestad.materialdialogs.MaterialDialog
import com.sarmady.contactkotlin.App
import com.sarmady.contactkotlin.R
import com.sarmady.contactkotlin.domain.entities.Article
import com.sarmady.contactkotlin.domain.entities.Language
import com.sarmady.contactkotlin.domain.entities.NewCar
import com.sarmady.contactkotlin.domain.entities.UsedCar
import com.sarmady.contactkotlin.domain.presenter.HomePresenter
import com.sarmady.contactkotlin.domain.view.HomeView
import com.sarmady.contactkotlin.ui.activity.BaseActivity
import com.sarmady.contactkotlin.ui.adapter.pager.ArticlesPagerAdapter
import com.sarmady.contactkotlin.ui.adapter.pager.CarAdPagerAdapter
import com.sarmady.contactkotlin.ui.adapter.recycler.HomeArticlesAdapter
import com.sarmady.contactkotlin.ui.util.setLayoutDirection
import com.sarmady.contactkotlin.ui.widget.SegmentedViewPager
import com.sarmady.fashiononwheels.ui.adapter.decorator.DividerItemDecorator
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    private lateinit var articlesVP: SegmentedViewPager
    private lateinit var newCarsVP: SegmentedViewPager
    private lateinit var usedCarsVP: SegmentedViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        stickyHeader.setUpAppBar(appbarLayout)

        setupActionBar()

        articlesVP = SegmentedViewPager(articlesRV)
        newCarsVP = SegmentedViewPager(newCarViewPager, newCarTabs)
        usedCarsVP = SegmentedViewPager(usedCarViewPager, usedCarTabs)
        latestArticles.adapter = HomeArticlesAdapter(arrayListOf())
        latestArticles.layoutManager = LinearLayoutManager(this)
        latestArticles.addItemDecoration(DividerItemDecorator(this))

        latestArticles.isNestedScrollingEnabled = false

        articlesVP.setAdapter(ArticlesPagerAdapter(supportFragmentManager))
        newCarsVP.setAdapter(CarAdPagerAdapter(supportFragmentManager))
        usedCarsVP.setAdapter(CarAdPagerAdapter(supportFragmentManager))
        articlesPagerIndicator.setViewPager(articlesRV)

        stickyHeader.onStartAnimationEnd {
            presenter.init()
        }

        setLayoutDirection(App.instance.language)
    }

    private fun setLayoutDirection(language: Language) {
        newCarViewPager.setLayoutDirection(language)
        usedCarViewPager.setLayoutDirection(language)
        articlesRV.setLayoutDirection(language)
        articlesPagerIndicator.setLayoutDirection(language)
    }

    private fun setupActionBar() {
        setSupportActionBar(stickyHeader.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun showLatestNewCars(cars: List<NewCar>) {
        newCarsVP.addSegment(cars, R.string.latest)
    }

    override fun showUpcomingNewCars(cars: List<NewCar>) {
        newCarsVP.addSegment(cars, R.string.upcoming)
    }

    override fun showPopularNewCars(cars: List<NewCar>) {
        newCarsVP.addSegment(cars, R.string.popular)
    }

    override fun showFeaturedArticles(articles: List<Article>) {
        articlesVP.addSegment(articles)
    }

    override fun showUsedCars(cars: List<UsedCar>, priceRange: String) {
        usedCarsVP.addSegment(cars, priceRange)
    }

    override fun showLatestArticles(articles: List<Article>) {
        (latestArticles.adapter as HomeArticlesAdapter).addMore(articles)
    }

    override fun showRetryDialog(onRetry: () -> Unit, onClose: () -> Unit) {
        runOnUiThread {
            MaterialDialog.Builder(this)
                    .content("Error")
                    .positiveText("Retry")
                    .negativeText("Close")
                    .onPositive { dialog, _ ->
                        onRetry()
                        dialog.dismiss()
                    }
                    .onNegative { dialog, _ ->
                        onClose()
                        dialog.dismiss()
                    }
                    .show()
        }
    }

    override fun closeView() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when (item?.itemId) {
                android.R.id.home -> {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START))
                        drawerLayout.closeDrawer(GravityCompat.START)
                    else drawerLayout.openDrawer(GravityCompat.START)

                    true
                }

                else -> {
                    false
                }
            }
}