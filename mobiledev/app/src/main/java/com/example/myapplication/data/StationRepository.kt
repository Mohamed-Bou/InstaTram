package com.example.myapplication.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.util.WEB_SERVICE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
class StationRepository(val app: Application) {
    val stationsData = MutableLiveData<Responsedata>()
    // 3. Appeler la méthode callWebService() en background en mode nonbloquant (via Coroutines)
    init {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }
    // 2. Méthode permettante de consommer le service web (via retrofit et Moshi)
    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .build()
            val service = retrofit.create(StationServices::class.java)
            val serviceData = service.getStationdata().body()
            if (serviceData != null) {
                stationsData.postValue(serviceData!! )
            }
            else{
                //  pour les test ,si le site ne reponde pas
                val station=Station("100","10","barca1","1t","l50","tmp","854","89")
                val station2=Station("101","10","barca2","1t","l50","tmp","854","89")
                val station3=Station("102","10","barca3","1t","l50","tmp","854","89")
                val station4=Station("103","10","barca4","1t","l50","tmp","854","89")
                val station5=Station("104","10","barca5","1t","l50","tmp","854","89")
                val tram= mutableListOf<Station>()
                tram.add(station)
                tram.add(station5)
                tram.add(station2)
                tram.add(station3)
                tram.add(station4)
                val data=Data(tram)
                val responsedata=Responsedata(200,data)
                stationsData.postValue(responsedata)
            }
        }
    }
    // 1. Vérifier si la connexion Internet est en marche ou pas
    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager =
            app.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}