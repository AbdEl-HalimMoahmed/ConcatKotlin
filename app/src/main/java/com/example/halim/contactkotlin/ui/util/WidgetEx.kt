package com.example.halim.contactkotlin.ui.util

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.halim.contactkotlin.di.module.app.GlideApp
import com.example.halim.contactkotlin.domain.entities.Arabic
import com.example.halim.contactkotlin.domain.entities.Language


fun ImageView.loadUrl(url: String?, transformation: Transformation<Bitmap>? = null,
                      onReady: (Bitmap) -> Unit = {}) {
    val glideRequest = GlideApp.with(context)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                                          isFirstResource: Boolean): Boolean = false

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                             dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    if (resource != null)
                        onReady((resource as BitmapDrawable).bitmap)
                    return false
                }

            })
            .centerCrop()

    if (transformation != null)
        glideRequest.transform(transformation)

    glideRequest.into(this)
}

fun View.setLayoutDirection(language: Language) : View {
    if (language is Arabic) {
        rotationY = 180f
    }

    return this
}

fun TabLayout.setLayoutDirection(language: Language): TabLayout {
    if (language is Arabic) {
        ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_LTR)
    }

    return this
}