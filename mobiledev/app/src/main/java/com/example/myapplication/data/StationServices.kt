package com.example.myapplication.data

import retrofit2.Response
import retrofit2.http.GET

interface StationServices {
    @GET("/b/608ae548f41a6a12bbf13eec")
    suspend fun getStationdata(): Response<Responsedata>
}