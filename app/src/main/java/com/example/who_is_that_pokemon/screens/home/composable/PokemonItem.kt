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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.who_is_that_pokemon.R
import com.example.who_is_that_pokemon.data.dto.PokemonDTO
import com.example.who_is_that_pokemon.data.dto.SpritesDTO

@Composable
fun PokemonItem(pokemonDTO: PokemonDTO, onClick: (String) -> Unit) {

    val isPreview = LocalInspectionMode.current

    Column(
        modifier = Modifier
            .heightIn(min = 200.dp)
            .padding(4.dp)
            .background(pokemonDTO.color, RoundedCornerShape(20.dp))
            .padding(12.dp)
            .clickable(onClick = {
                onClick(pokemonDTO.name)
            }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (isPreview) {
            Image(
                painter = painterResource(R.drawable.pikachu_preview),
                contentDescription = "Pikachu",
                modifier = Modifier.size(150.dp)
            )
        } else {
            AsyncImage(
                model = pokemonDTO.spritesDTO.default,
                contentDescription = pokemonDTO.name,
                modifier = Modifier.size(150.dp)
            )
        }

        Text(
            text = pokemonDTO.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "# " + pokemonDTO.id,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
private fun PokemonItemPreview() {
    val mockPokemon = PokemonDTO(
        id = 25,
        name = "pikachu",
        height = 0.4,
        weight = 6.0,
        types = emptyList(),
        spritesDTO = SpritesDTO(""),
        stats = emptyList(),
        color = Color.Yellow
    )
    PokemonItem(mockPokemon) {}
}