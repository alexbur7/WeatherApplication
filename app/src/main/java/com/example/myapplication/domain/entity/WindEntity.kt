package com.example.myapplication.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WindEntity(
    val speed: Float
):Parcelable
