package com.phelat.splash.photopreview.fragment

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phelat.splash.R
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.utils.inflate
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_list_item.*
import kotlinx.android.synthetic.main.photo_list_shimmer.*

/**
 * Created by MAHDi on 6/21/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoPreviewFragment : Fragment() {

    companion object {

        private const val ID = "id"
        private const val URL = "url"

        fun instantiate(data: PhotoEntity): PhotoPreviewFragment {

            // TODO : USE PARCELABLE TO PASS OBJECT
            val bundle = Bundle().apply {
                putString(ID, data.id)
                putString(URL, data.photoUrls?.regular)
            }

            return PhotoPreviewFragment().apply {
                arguments = bundle
            }
        }

    }

    private val loadingEvaluatorDuration = 1000L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.photo_list_item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorAnim = ObjectAnimator.ofInt(splashShimmer,
                "cardBackgroundColor",
                Color.parseColor("#F5F5F5"),
                Color.parseColor("#E0E0E0")).apply {
            duration = loadingEvaluatorDuration
            setEvaluator(ArgbEvaluator())
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
        colorAnim.start()

        if (arguments?.getString(URL, "") != "") {
            Picasso.with(activity?.applicationContext)
                    .load(arguments?.getString(URL))
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

    override fun onDestroyView() {
        Picasso.with(activity?.applicationContext)
                .cancelRequest(splash)
        super.onDestroyView()
    }

}