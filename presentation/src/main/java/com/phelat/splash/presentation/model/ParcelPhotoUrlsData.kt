package com.phelat.splash.presentation.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ParcelPhotoUrlsData(

        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String

) : Parcelable