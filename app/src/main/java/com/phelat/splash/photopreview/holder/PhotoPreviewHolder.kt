package com.phelat.splash.photopreview.holder

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.phelat.splash.data.entity.PhotoEntity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_list_item.view.*
import kotlinx.android.synthetic.main.photo_list_shimmer.view.*

/**
 * Created by MAHDi on 7/13/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoPreviewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val splash: AppCompatImageView = view.splash

    val splashShimmer: CardView = view.splashShimmer

    val splashCard: CardView = view.splashCard

    private val loadingEvaluatorDuration = 1000L

    private val colorAnim by lazy(LazyThreadSafetyMode.NONE) {
        ObjectAnimator.ofInt(splashShimmer,
                "cardBackgroundColor",
                Color.parseColor("#F5F5F5"),
                Color.parseColor("#E0E0E0")).apply {
            duration = loadingEvaluatorDuration
            setEvaluator(ArgbEvaluator())
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
    }

    fun bind(photoEntity: PhotoEntity) {

        colorAnim.start()

        photoEntity.photoUrls?.let { photoUrlsData ->
            Picasso.with(splash.context.applicationContext)
                    .load(photoUrlsData.regular)
                    .into(splash, object : Callback {
                        override fun onSuccess() {
                            splashCard.visibility = View.VISIBLE
                            splashShimmer.visibility = View.GONE
                            colorAnim.cancel()
                        }

                        override fun onError() {
                            splashShimmer.visibility = View.GONE
                            colorAnim.cancel()
                        }
                    })
        }
    }

    fun onDestroy() {
        colorAnim.cancel()
        Picasso.with(splash.context.applicationContext)
                .cancelRequest(splash)
    }

}