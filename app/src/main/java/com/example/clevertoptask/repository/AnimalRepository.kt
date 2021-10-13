package com.example.clevertoptask.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.clevertoptask.model.Animal
import com.example.clevertoptask.retrofit.ApiClient
import com.example.clevertoptask.retrofit.ApiInterface
import com.example.clevertoptask.roomdatabase.AnimalDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalRepository {
    private var coroutineScope = CoroutineScope(SupervisorJob())
    private var animalLiveData = MutableLiveData<Animal>()
    val animal : LiveData<Animal>
        get() {
            return animalLiveData
        }
    private var animalListLiveData = MutableLiveData<List<Animal>>()
    val animalList : LiveData<List<Animal>>
        get() {
            return animalListLiveData
        }

    fun getAnimalImage(context: Application) {
        val apiInterface = ApiClient.getInstance().create(ApiInterface::class.java)
        val call = apiInterface.getAnimalDetails()
        call.enqueue(object : Callback<Animal>{
            override fun onResponse(call: Call<Animal>, response: Response<Animal>) {
                val animal = response.body()
                animalLiveData.postValue(animal)
                saveImage(context,animal)
                getList(context)
            }

            override fun onFailure(call: Call<Animal>, t: Throwable) {

            }

        })
    }

     fun getList(context: Application) {
        coroutineScope.launch {
            val list = AnimalDatabase.getInstance(context).getAnimalDetailDao().getAnimalList()
            animalListLiveData.postValue(list)
        }
    }

    private fun saveImage(context: Context, animal: Animal?) {
        coroutineScope.launch {
            if (animal != null) {
                AnimalDatabase.getInstance(context).getAnimalDetailDao().insert(animal)
            }
        }
    }

    fun showPrevious(context: Application) {
        coroutineScope.launch {
            val list = AnimalDatabase.getInstance(context).getAnimalDetailDao().getAnimalList()
            animalListLiveData.postValue(list)
        }
    }
}