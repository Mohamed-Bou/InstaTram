package com.example.myapplication.ui.Ecran1.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.data.StationRepository

class HomeViewModel(app: Application) : AndroidViewModel(app) {
    private val dataRepo = StationRepository(app)
    val stationData = dataRepo.stationsData
}