package com.example.myapplication.localdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Image::class],version=1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class ImageDatabase : RoomDatabase(){
    abstract fun imageDao():ImageDao
    companion object{
        @Volatile
        private var INSTANCE:ImageDatabase?=null
        fun getDatabase(context: Context):ImageDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    ImageDatabase::class.java,
                    "image"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}