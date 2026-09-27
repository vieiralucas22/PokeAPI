package com.example.who_is_that_pokemon.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.who_is_that_pokemon.R
import com.example.who_is_that_pokemon.screens.common.model.PokemonColorState
import com.example.who_is_that_pokemon.screens.common.model.PokemonState
import com.example.who_is_that_pokemon.screens.common.model.SpecieDetailsState
import com.example.who_is_that_pokemon.screens.common.model.SpritesState

@Composable
fun PokemonItem(state: PokemonState, onClick: (String) -> Unit) {

    val isPreview = LocalInspectionMode.current

    Column(
        modifier = Modifier
            .heightIn(min = 200.dp)
            .padding(4.dp)
            .background(
                colorResource(getPokemonColorId(state.specieDetails.pokemonColor.colorName)),
                RoundedCornerShape(20.dp)
            )
            .padding(12.dp)
            .clickable(onClick = {
                onClick(state.name)
            }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (isPreview) {
            Image(
                painter = painterResource(R.drawable.pikachu_preview),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
        } else {
            AsyncImage(
                model = state.sprites.default,
                contentDescription = state.name,
                modifier = Modifier.size(150.dp)
            )
        }

        Text(
            text = state.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "# " + state.id,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

fun getPokemonColorId(color: String): Int {
    return when (color.lowercase()) {
        "red" -> R.color.pokemon_red
        "blue" -> R.color.pokemon_blue
        "yellow" -> R.color.pokemon_yellow
        "green" -> R.color.pokemon_green
        "black" -> R.color.pokemon_black
        "white" -> R.color.pokemon_white
        "gray" -> R.color.pokemon_gray
        "pink" -> R.color.pokemon_pink
        "purple" -> R.color.pokemon_purple
        "brown" -> R.color.pokemon_brown
        else -> R.color.pokemon_default
    }
}

@Preview
@Composable
private fun PokemonItemPreview() {
    val mockPokemon = PokemonState(
        id = 25,
        name = "pikachu",
        height = 0.4,
        weight = 6.0,
        types = emptyList(),
        sprites = SpritesState(
            default = "",
            shiny = ""
        ),
        stats = emptyList(),
        specieDetails = SpecieDetailsState(
            pokemonColor = PokemonColorState("yellow"),
            descriptions = emptyList()
        )
    )
    PokemonItem(mockPokemon) {}
}