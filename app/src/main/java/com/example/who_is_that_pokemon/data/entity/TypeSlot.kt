package com.example.who_is_that_pokemon.data.entity

import com.google.gson.annotations.SerializedName

data class TypeSlot(
    @SerializedName("slot")
    var slot: Int,
    @SerializedName("type")
    var type: Type
)
