package com.example.who_is_that_pokemon.dsm.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.who_is_that_pokemon.R

@Composable
fun ErrorComponent(errorMessage: String) {
    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 24.sp,
            color = colorResource(R.color.pokemon_black),
        )

        Image(
            modifier = Modifier.width(300.dp),
            painter = painterResource(R.drawable.not_found),
            contentDescription = null
        )

        Text(
            text = "Please try again later",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 24.sp,
            color = colorResource(R.color.pokemon_black),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotFoundPokemonComponentPreview() {
    ErrorComponent("Pokemon not found!")
}