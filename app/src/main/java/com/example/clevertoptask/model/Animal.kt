package com.example.clevertoptask.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Animal(@PrimaryKey(autoGenerate = true) var id : Int, var message : String, var status : String)