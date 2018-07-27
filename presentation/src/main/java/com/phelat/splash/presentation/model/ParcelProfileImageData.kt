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
data class ParcelProfileImageData(

        val small: String,
        val medium: String,
        val large: String

) : Parcelable