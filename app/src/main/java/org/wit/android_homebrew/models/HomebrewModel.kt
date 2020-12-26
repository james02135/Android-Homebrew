package org.wit.android_homebrew.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomebrewModel(
        var id: Long = 0,
        var name: String = "",
        var style: String = ""
) : Parcelable