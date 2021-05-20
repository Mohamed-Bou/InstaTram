package com.example.myapplication.ui.Ecran1.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.ui.Ecran1.home.HomeViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private lateinit var homeViewModel: HomeViewModel
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        mMap=googleMap
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.stationData.observe(
                this,{
            val stations =it.data.tram
            for (station in stations) {
                val stat=LatLng(station.lat.toDouble(),station.lon.toDouble())
                mMap.addMarker(MarkerOptions().position(stat).title(station.name))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stat,15f))
            }
            mMap.setOnMarkerClickListener { marker ->
                val stationName=marker.title
                var idStation=21
                //id de la station pour passer a l'ecran2
                for (station in stations){
                    if(station.name==stationName){
                        idStation=station.id.toInt()
                    }
                }
                val action =MapsFragmentDirections.actionMapsFragmentToEcran2(idStation,stationName)
                findNavController().navigate(action)
                true
            }
        }
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}