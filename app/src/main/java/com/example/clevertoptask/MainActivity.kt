package com.example.clevertoptask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.clevertoptask.databinding.ActivityMainBinding
import com.example.clevertoptask.model.Animal
import com.example.clevertoptask.viewmodel.AnimalViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AnimalViewModel
    private lateinit var mainBinding : ActivityMainBinding
    private var list : List<Animal> = ArrayList()
    private var currentIndex : Int = list.size - 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        viewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)

        viewModel.showPrevious()
        if (currentIndex <= 0)  mainBinding.btnPrevious.isEnabled = false

        viewModel.animal.observe(this) {
            mainBinding.progressBar.visibility = View.GONE
            setImage(it.message)
        }

        viewModel.animalList.observe(this) {
            list = it
            if (it == null) {
                mainBinding.btnPrevious.visibility = View.GONE
            } else {
                mainBinding.btnPrevious.visibility = View.VISIBLE
            }
        }

        mainBinding.btnNext.setOnClickListener {
            if (currentIndex < list.size - 1) {
                currentIndex++
                setImage(list[currentIndex].message)
                mainBinding.btnPrevious.isEnabled = currentIndex != 0
                mainBinding.progressBar.visibility = View.GONE
            } else {
                mainBinding.progressBar.visibility = View.VISIBLE
                viewModel.getAnimalImage()
                mainBinding.btnPrevious.isEnabled = true
            }

        }
        mainBinding.btnPrevious.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                setImage(list[currentIndex].message)
                mainBinding.btnPrevious.isEnabled = currentIndex != 0
            } else {
                mainBinding.btnPrevious.isEnabled = false
            }

        }


    }

    private fun setImage(animal: String) {
        Glide.with(this).load(animal).placeholder(R.drawable.ic_placeholder_image_24).into(mainBinding.ivAvatar)
    }
}