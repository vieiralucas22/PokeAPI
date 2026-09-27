package com.example.who_is_that_pokemon.data.dto

import com.google.gson.annotations.SerializedName

data class TypeSlotDTO(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeDTO
)
