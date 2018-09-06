package com.phelat.splash.presentation.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ParcelProfileImageData(

        val small: String,
        val medium: String,
        val large: String

) : Parcelable