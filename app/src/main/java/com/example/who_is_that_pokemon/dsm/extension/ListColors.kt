package com.example.who_is_that_pokemon.dsm.extension

import androidx.compose.ui.graphics.Color

fun List<Color>.mix() : Color {
    if (isEmpty()) return Color(0xFFD3D3D3)

    return Color(
        red = map { it.red }.average().toFloat(),
        green = map { it.green }.average().toFloat(),
        blue = map { it.blue }.average().toFloat(),
        alpha = 1f
    )
}