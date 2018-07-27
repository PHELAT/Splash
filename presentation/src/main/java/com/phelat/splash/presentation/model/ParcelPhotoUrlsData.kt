package com.phelat.splash.presentation.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by MAHDi on 7/13/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class ParcelPhotoUrlsData(

        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String

) : Parcelable