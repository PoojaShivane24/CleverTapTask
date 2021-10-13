package com.example.clevertoptask.retrofit

import com.example.clevertoptask.model.Animal
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/breeds/image/random")
    fun getAnimalDetails(): Call<Animal>

}