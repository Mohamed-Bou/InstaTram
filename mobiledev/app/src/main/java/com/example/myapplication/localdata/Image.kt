package com.example.myapplication.localdata

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class Image(
        @PrimaryKey(autoGenerate = true)
        val id_image:Int,
        val id_station:Int,
        val date:String,
        val titre:String,
        val photo: Bitmap,
)