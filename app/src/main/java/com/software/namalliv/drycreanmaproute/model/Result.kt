package com.software.namalliv.drycreanmaproute.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    var distance: String,
    var time: String
): Parcelable