package com.sarmady.contactkotlin

import android.app.Activity
import android.app.Application
import android.app.Service
import cn.hikyson.android.godeye.toolbox.crash.CrashFileProvider
import cn.hikyson.godeye.core.GodEye
import cn.hikyson.godeye.monitor.GodEyeMonitor
import com.sarmady.contactkotlin.di.component.app.DaggerAppComponent
import com.sarmady.contactkotlin.domain.entities.Arabic
import com.sarmady.contactkotlin.domain.entities.Language
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var language: Language

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().create(this).inject(this)

        instance = this

        getRegId()

        setFont()

        // TODO Inject
        GodEye.instance().installAll(this, CrashFileProvider(this))
        GodEyeMonitor.work(this)
    }

    private fun setFont() {
        if (language is Arabic)
            CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/DroidSansArabic.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build())
    }

    // TODO get Google RegID (Service)
    private fun getRegId() {
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun serviceInjector(): AndroidInjector<Service> = serviceInjector
}