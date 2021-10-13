package com.example.clevertoptask.roomdatabase

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.clevertoptask.model.Animal
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class AnimalDatabaseTest : TestCase() {
    lateinit var db : AnimalDatabase
    lateinit var dao : Dao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AnimalDatabase::class.java).build()

        dao = db.getAnimalDetailDao()


    }

    @Test
    fun writeAndReadAnimal() {
        val animal = Animal(1, "abc", "success")
        dao.insert(animal)

        val animalList = dao.getAnimalList()

        assertThat(animalList.contains(animal)).isTrue()
    }

    @After
    fun closeDb() {
        db.close()
    }
}