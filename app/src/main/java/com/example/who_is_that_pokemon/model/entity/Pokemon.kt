package com.example.who_is_that_pokemon.model.entity

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("height")
    val height : Double,
    @SerializedName("weight")
    val weight : Double,
    @SerializedName("types")
    val types : List<TypeSlot>,
    @SerializedName("sprites")
    val sprites : Sprites,
    @SerializedName("stats")
    val stats : List<Stats>
)

data class TypeSlot (
    @SerializedName("slot")
    val slot : Int,
    @SerializedName("type")
    val type : Type
)

data class Type (
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class Sprites(
    @SerializedName("front_default")
    val default : String,
    @SerializedName("front_shiny")
    val shiny : String
)

data class Stats(
    @SerializedName("base_stat")
    val value : Int,
    @SerializedName("stat")
    val stat : Stat
)

data class Stat(
    @SerializedName("name")
    val statName : String,
)
