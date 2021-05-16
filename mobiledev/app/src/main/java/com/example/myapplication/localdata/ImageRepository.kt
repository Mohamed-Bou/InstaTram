package com.example.myapplication.localdata

import androidx.lifecycle.LiveData

class ImageRepository(private val imageDao:ImageDao) {
    val StationImages: LiveData<List<Image>> =imageDao.getImages()
}