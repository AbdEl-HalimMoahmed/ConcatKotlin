package com.sarmady.contactkotlin.ui.activity

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sarmady.contactkotlin.App
import com.sarmady.contactkotlin.domain.entities.Language
import com.sarmady.contactkotlin.domain.view.View
import dagger.android.AndroidInjection
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.util.*


abstract class BaseActivity : AppCompatActivity(), View {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(updateLanguage(newBase, App.instance.language)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    private fun updateLanguage(base: Context, language: Language): ContextWrapper {
        var baseContext = base
        val resources = base.resources
        val conf = resources.configuration
        val locale = language.local
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(locale)
            conf.setLayoutDirection(locale)
            baseContext = baseContext.createConfigurationContext(conf)
        } else {
            conf.locale = locale
            resources.updateConfiguration(conf, resources.displayMetrics)
        }
        return ContextWrapper(baseContext)
    }
}