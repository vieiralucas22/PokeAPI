package com.example.who_is_that_pokemon.data.entity

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("name")
    var name: String,
)
