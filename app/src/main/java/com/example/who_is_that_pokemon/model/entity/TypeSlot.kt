package com.example.who_is_that_pokemon.model.entity

import com.google.gson.annotations.SerializedName

data class TypeSlot(
    @SerializedName("slot")
    var slot: Int,
    @SerializedName("type")
    var type: Type
)
