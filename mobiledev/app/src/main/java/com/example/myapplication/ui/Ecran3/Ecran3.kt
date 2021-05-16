package com.example.myapplication.ui.Ecran3

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.localdata.Image
import com.example.myapplication.ui.Ecran2.ImageViewModel
import com.example.myapplication.util.Obj
import java.text.SimpleDateFormat
import java.util.*

class Ecran3 : AppCompatActivity() {
    private val sdf= SimpleDateFormat("yyyy/mm/dd HH:mm:ss")
    private lateinit var imageViewModel: ImageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecran3)
        val photo=findViewById<ImageView>(R.id.station_image)

        var station_image:Bitmap
        var id_station:Int
        imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        val btn_save=findViewById<Button>(R.id.save)
        station_image = intent.getParcelableExtra<Obj>("photo")!!.photo
        photo.setImageBitmap(station_image)
        btn_save.setOnClickListener {
            val titre=findViewById<EditText>(R.id.image_titre).text
            val cal = Calendar.getInstance()
            val date=sdf.format(cal.time)
            if(intent.getParcelableExtra<Obj>("photo")!=null){

                id_station= intent.getParcelableExtra<Obj>("photo")!!.id_station

                val image= Image(0,id_station,date,titre.toString(), station_image)
                imageViewModel.addImage(image)
                val intent=Intent()
                intent.putExtra("message","saved")
                setResult(RESULT_OK,intent)
                finish()
            }

        }
        supportActionBar?.title=""
    }
}