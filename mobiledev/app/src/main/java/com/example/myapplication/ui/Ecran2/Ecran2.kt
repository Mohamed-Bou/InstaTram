package com.example.myapplication.ui.Ecran2

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import androidx.recyclerview.widget.*
import com.example.myapplication.R
import com.example.myapplication.localdata.Image
import com.example.myapplication.ui.Ecran3.Ecran3
import com.example.myapplication.util.Obj
import com.example.myapplication.util.SwipToRemove

class Ecran2 : AppCompatActivity() {
    val args:Ecran2Args by navArgs()
    private lateinit var imageViewModel: ImageViewModel
    private lateinit var adapter: ImagesAdapter


    companion object{
        private const val CAMERA_PERMISSION_CODE=1
        private const val CAMERA_REQUEST_CODE=2
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecran2)
        val tt=findViewById<TextView>(R.id.StationName)
        tt.text=args.stationName
        val recyclerView = findViewById<RecyclerView>(R.id.rv_image)
        recyclerView.layoutManager=GridLayoutManager(applicationContext,2)
        imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        imageViewModel.stationImages.observe(this, Observer {
            //
            val station_images= mutableListOf<Image>()
            for (image in it) {
                if (image.id_station == args.idStation) {
                    station_images.add(image)
                }
            }
            adapter = ImagesAdapter(station_images)
            recyclerView.adapter = adapter
            var itemTouchHelper=ItemTouchHelper(SwipToRemove(adapter,imageViewModel))
            itemTouchHelper.attachToRecyclerView(recyclerView)
        })

        val btn_ajouter=findViewById<ImageButton>(R.id.btn_ajouter)
        btn_ajouter.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            {
                val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),2)
            }
        }
        supportActionBar?.hide()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if( requestCode== CAMERA_PERMISSION_CODE){
            if(grantResults.isEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{
                Toast.makeText(
                        this,
                        "allow the camera",
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            if(requestCode== CAMERA_REQUEST_CODE){
                val thumBnail:Bitmap= data?.extras?.get("data")
                        as Bitmap
                //
                val intent=Intent(this,Ecran3::class.java)
                val photo= Obj(thumBnail,args.idStation)
                intent.putExtra("photo",photo)
                startActivityForResult(intent,55)

            }
            if(requestCode==55){
                val mess= data?.getStringExtra("message")
                if (mess != null) {
                    Toast.makeText(this,mess,Toast.LENGTH_LONG).show()
                }
            }
        }

    }

}