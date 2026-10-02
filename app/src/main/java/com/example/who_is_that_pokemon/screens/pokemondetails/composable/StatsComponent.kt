package com.example.who_is_that_pokemon.screens.pokemondetails.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.who_is_that_pokemon.R
import com.example.who_is_that_pokemon.screens.common.model.StatState
import com.example.who_is_that_pokemon.screens.common.model.StatsState

@Composable
fun StatsComponent(state: StatsState, color: Color) {

    Spacer(Modifier.height(8.dp))

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.stat.statName.replaceFirstChar { it.uppercase() }
                .replace("Special-", "Sp.") //TODO: Avoid this code
                .replace("attack", "atk").replace("defense", "def"),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(0.24f),
            color = color
        )

        Text(
            text = state.value.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(0.14f)
        )

        LinearProgressIndicator(
            progress = { state.value / 255f },
            modifier = Modifier
                .weight(0.60f)
                .height(8.dp),
            color = color,
            trackColor = color.copy(alpha = 0.25f),
            gapSize = 0.dp,
            strokeCap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true, heightDp = 50)
@Composable
private fun StatsPreview() {
    val mockState = StatsState(
        value = 0.55f,
        stat = StatState("hp")
    )

    StatsComponent(mockState, Color.Yellow)
}