package com.example.clevertoptask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.clevertoptask.model.Animal
import com.example.clevertoptask.repository.AnimalRepository

class AnimalViewModel(application: Application) : AndroidViewModel(application) {
    private var context = application
    private var repo : AnimalRepository = AnimalRepository()

    val animal : LiveData<Animal>
        get() {
            return repo.animal
        }

    val animalList : LiveData<List<Animal>>
        get() {
            return repo.animalList
        }

    fun getAnimalImage() {
        repo.getAnimalImage(context)
    }



    fun showPrevious() {
        repo.showPrevious(context)
    }


}