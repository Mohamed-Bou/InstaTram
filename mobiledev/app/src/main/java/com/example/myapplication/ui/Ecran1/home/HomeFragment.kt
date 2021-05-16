package com.example.myapplication.ui.Ecran1.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HomeFragment : Fragment(){


    private lateinit var homeViewModel: HomeViewModel
    private  lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val progressBare=view.findViewById<ProgressBar>(R.id.progressBar)
        progressBare.isVisible=true
        recyclerView = view.findViewById(R.id.rv)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.stationData.observe(viewLifecycleOwner, Observer
        { it ->
            val adapter = StationAdapter(it.data.tram)
            recyclerView.adapter = adapter
            progressBare.isVisible=false
            adapter.setOnItemClickListener {
                val action=HomeFragmentDirections.actionNavigationHomeToEcran2(it.id.toInt(),it.name)
                Log.d("her",it.name)
                findNavController().navigate(action)
            }

        })
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}