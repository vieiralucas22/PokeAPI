package com.example.who_is_that_pokemon.model.entity

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("height")
    var height : Double,
    @SerializedName("weight")
    var weight : Double,
    @SerializedName("types")
    var types : List<TypeSlot>,
    @SerializedName("sprites")
    var sprites : Sprites,
    @SerializedName("stats")
    var stats : List<Stats>,
    var color : Color
)
