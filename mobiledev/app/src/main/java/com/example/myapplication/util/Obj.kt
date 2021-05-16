package com.example.myapplication.util

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Obj(
    val photo:Bitmap,
    val id_station:Int,
): Parcelable