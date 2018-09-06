package com.phelat.splash.photopreview.holder

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.utils.displayMetrics
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_list_item.view.*
import kotlinx.android.synthetic.main.photo_list_shimmer.view.*
import kotlin.math.roundToInt

class PhotoPreviewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {

        const val REQUIRED_WIDTH = 1.3

        const val LOADING_EVALUATOR_DURATION = 1000L

    }

    val root: ConstraintLayout = view.root

    val splash: AppCompatImageView = view.splash

    val splashShimmer: CardView = view.splashShimmer

    val splashCard: CardView = view.splashCard

    private val colorAnim by lazy(LazyThreadSafetyMode.NONE) {
        ObjectAnimator.ofInt(splashShimmer,
                "cardBackgroundColor",
                Color.parseColor("#F5F5F5"),
                Color.parseColor("#E0E0E0")).apply {
            duration = LOADING_EVALUATOR_DURATION
            setEvaluator(ArgbEvaluator())
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
    }

    fun bind(photoEntity: PhotoEntity) {

        splashCard.visibility = View.GONE
        splashShimmer.visibility = View.VISIBLE

        colorAnim.start()

        calculateRootWidth()

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

    private fun calculateRootWidth() {

        val displayMetrics = root.displayMetrics()

        val width = displayMetrics.widthPixels / REQUIRED_WIDTH

        val params = root.layoutParams
        params.width = width.roundToInt()

        root.layoutParams = params
    }

}