package com.example.myapplication.ui.Ecran2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.localdata.Image
import com.example.myapplication.localdata.ImageDao
import com.example.myapplication.localdata.ImageDatabase
import com.example.myapplication.localdata.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel(application: Application): AndroidViewModel(application) {
    var  stationImages: LiveData<List<Image>>
    private var repository: ImageRepository
    private var imageDoa:ImageDao
    init {
        imageDoa= ImageDatabase.getDatabase(application).imageDao()
        repository= ImageRepository(imageDoa)
        stationImages=repository.StationImages
    }
    fun addImage(image: Image){
        viewModelScope.launch(Dispatchers.IO){
            imageDoa.addImage(image)
        }
    }
    fun delete(image: Image){
        viewModelScope.launch(Dispatchers.IO){
            imageDoa.delet(image)
        }
    }
}