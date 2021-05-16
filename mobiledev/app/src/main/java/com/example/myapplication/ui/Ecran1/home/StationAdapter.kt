package com.example.myapplication.ui.Ecran1.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Station

class StationAdapter(
        var stationlist: List<Station>,
):RecyclerView.Adapter<StationAdapter.StationviewHolder>() {

    inner class StationviewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationviewHolder {
        return StationviewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_station,
                        parent,
                        false
                )
        )
    }

    private var onItemClickListener: ((Station) -> Unit)? = null
    override fun onBindViewHolder(holder: StationviewHolder, position: Int) {
        val station = stationlist[position]
        holder.itemView.apply {
            val name=this.findViewById<TextView>(R.id.name)
            name?.let {
                it.text = station.name
            }
            val id=this.findViewById<TextView>(R.id.id)
            id?.let{
                it.text=station.id
            }
            setOnClickListener {
                onItemClickListener?.let { it(station) }
            }
        }
    }
    fun setOnItemClickListener(listener: (Station) -> Unit) {
        onItemClickListener = listener
    }
    override fun getItemCount(): Int {
        return stationlist.size
    }




}