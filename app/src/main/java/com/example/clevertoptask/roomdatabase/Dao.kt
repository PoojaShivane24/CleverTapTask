package com.example.clevertoptask.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.clevertoptask.model.Animal

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: Animal) : Long

    @Query("Select * from Animal")
    fun getAnimalList() : List<Animal>
}