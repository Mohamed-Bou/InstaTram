package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.ui.Ecran1.Ecran1
import com.example.myapplication.R

class acueill : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acueill)
        supportActionBar?.hide()
    }
    fun derictToecran1(view: View) {
        val intent = Intent(this,
            Ecran1::class.java)
        startActivity(intent)
    }


}