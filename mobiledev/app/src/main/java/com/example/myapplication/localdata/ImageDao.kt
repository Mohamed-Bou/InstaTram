package com.example.myapplication.localdata

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImage(image: Image)

    @Query("SELECT * from image")
    fun getImages(): LiveData<List<Image>>

    @Delete
    suspend fun delet(image: Image)


}