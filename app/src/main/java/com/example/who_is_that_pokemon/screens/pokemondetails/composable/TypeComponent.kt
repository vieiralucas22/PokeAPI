package com.example.who_is_that_pokemon.screens.pokemondetails.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.who_is_that_pokemon.R
import com.example.who_is_that_pokemon.screens.common.model.TypeState

@Composable
fun TypeComponent(state: TypeState) {
    Column(
        modifier = Modifier
            .widthIn(min = 50.dp)
            .background(colorResource(state.getTypeColor()), RoundedCornerShape(24.dp))
            .padding(4.dp, 1.dp, 4.dp, 1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.white),
            fontSize = 12.sp
        )
    }

    Spacer(Modifier.width(4.dp))
}

@Preview(showBackground = true, widthDp = 50)
@Composable
private fun TypeComponentPreview() {
    val mockState = TypeState("Electric")
    TypeComponent(mockState)
}