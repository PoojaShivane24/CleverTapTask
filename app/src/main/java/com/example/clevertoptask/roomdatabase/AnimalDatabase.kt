package com.example.clevertoptask.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clevertoptask.model.Animal

@Database(entities = [Animal::class], version = 1, exportSchema = false)
abstract class AnimalDatabase : RoomDatabase() {
    abstract fun getAnimalDetailDao() : Dao
    companion object {
        private var animalDatabase: AnimalDatabase? = null

        fun getInstance(context: Context): AnimalDatabase {
            val tempInstance = animalDatabase
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, AnimalDatabase::class.java, "animalDatabase").build()
                animalDatabase = instance
                return instance
            }
        }
    }
}