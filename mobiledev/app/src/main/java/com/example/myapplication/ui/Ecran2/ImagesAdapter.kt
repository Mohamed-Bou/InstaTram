package com.example.myapplication.ui.Ecran2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Station
import com.example.myapplication.localdata.Image
import com.example.myapplication.localdata.ImageDatabase

class ImagesAdapter(
        val imageslist: MutableList<Image>
):RecyclerView.Adapter<ImagesAdapter.StationviewHolder>() {

    private lateinit var imageViewModel: ImageViewModel
    inner class StationviewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationviewHolder {
        return StationviewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_image,
                        parent,
                        false
                )

        )
    }
    override fun onBindViewHolder(holder: StationviewHolder, position: Int) {
        val image = imageslist[position]
        holder.itemView.apply {
            val titre=this.findViewById<TextView>(R.id.titre)
            titre?.let {
                it.text = image.titre
            }
            val date=this.findViewById<TextView>(R.id.date)
            date?.let{
                it.text=image.date
            }
            val photo=this.findViewById<ImageView>(R.id.station_photo)
            photo?.let{
                it.setPadding(0)
                it.setImageBitmap(image.photo)
            }
        }
    }
    override fun getItemCount(): Int {
        return imageslist.size
    }
    fun delet(position: Int,viewModel: ImageViewModel){
        viewModel.delete(imageslist[position])
        imageslist.removeAt(position)
        notifyItemRemoved(position)
    }
}