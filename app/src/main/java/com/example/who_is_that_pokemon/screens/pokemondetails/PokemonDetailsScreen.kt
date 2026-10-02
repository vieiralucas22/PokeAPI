package com.example.who_is_that_pokemon.screens.pokemondetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.who_is_that_pokemon.R
import com.example.who_is_that_pokemon.dsm.animation.LoadingAnimation
import com.example.who_is_that_pokemon.dsm.composable.ErrorComponent
import com.example.who_is_that_pokemon.dsm.extension.mix
import com.example.who_is_that_pokemon.screens.common.model.PokemonDescriptionState
import com.example.who_is_that_pokemon.screens.common.model.PokemonState
import com.example.who_is_that_pokemon.screens.common.model.SpecieDetailsState
import com.example.who_is_that_pokemon.screens.common.model.SpritesState
import com.example.who_is_that_pokemon.screens.common.model.StatState
import com.example.who_is_that_pokemon.screens.common.model.StatsState
import com.example.who_is_that_pokemon.screens.common.model.TypeSlotState
import com.example.who_is_that_pokemon.screens.common.model.TypeState
import com.example.who_is_that_pokemon.screens.pokemondetails.composable.StatsComponent
import com.example.who_is_that_pokemon.screens.pokemondetails.composable.TypeComponent
import com.example.who_is_that_pokemon.screens.pokemondetails.model.PokemonDetailsUIState
import kotlin.text.replaceFirstChar

@Composable
fun PokemonDetailsScreen(viewModel: PokemonDetailsViewModel, onBackButtonClick: () -> Unit) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Content(uiState) { onBackButtonClick() }
}

@Composable
fun Content(uiState: PokemonDetailsUIState, onBackButtonClick: () -> Unit) {

    Scaffold(
        topBar = {
            HeaderView(uiState) {
                onBackButtonClick()
            }
        },
        content = { paddingValues ->
            Column {
                when (uiState) {
                    is PokemonDetailsUIState.Success -> {
                        val color = listOf(
                            colorResource(uiState.data.getPokemonColor() ?: R.color.white),
                            colorResource(R.color.white)
                        ).mix()

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(paddingValues)
                        )
                        {
                            SuccessContent(uiState.data)
                        }
                    }

                    is PokemonDetailsUIState.Loading -> Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        LoadingAnimation(
                            circleSize = 50.dp,
                            travelDistance = 40.dp,
                            spaceBetween = 12.dp
                        )
                    }

                    is PokemonDetailsUIState.Error -> Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        ErrorComponent("Pokemon not found!")
                    }
                }
            }
        }
    )
}

@Composable
fun SuccessContent(pokemonState: PokemonState) {
    val isPreview = LocalInspectionMode.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.pokeball_icon),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(250.dp)
                .alpha(0.15f)
                .rotate(315f)
        )

        if (isPreview) {
            Image(
                painter = painterResource(R.drawable.pikachu_preview),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
            )
        } else {
            AsyncImage(
                model = pokemonState.sprites.default,
                contentDescription = pokemonState.name,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center)
            )
        }

    }
    MainView(pokemonState)
}

@Composable
fun HeaderView(uiState: PokemonDetailsUIState, onBackButtonClick: () -> Unit) {

    var pokemonName by rememberSaveable { mutableStateOf("") }
    var pokemonNationalIndex by rememberSaveable { mutableIntStateOf(0) }
    var pokemonColorId by rememberSaveable { mutableIntStateOf(R.color.pokemon_black) }

    if (uiState is PokemonDetailsUIState.Success) {
        pokemonName = uiState.data.name
        pokemonNationalIndex = uiState.data.id
        pokemonColorId = R.color.white
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            IconButton(onClick = { onBackButtonClick() }) {
                Icon(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = null,
                    tint = colorResource(pokemonColorId)
                )
            }

            Spacer(Modifier.width(4.dp))

            Text(
                text = pokemonName.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleMedium,
                fontSize = 16.sp,
                color = colorResource(R.color.white)
            )
        }

        Text(
            text = "# $pokemonNationalIndex",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.white),
            fontSize = 16.sp,
        )
    }
}

@Composable
fun MainView(pokemonState: PokemonState) {
    val types = pokemonState.types
    val stats = pokemonState.stats
    val description = pokemonState.specieDetails.descriptions[0].text
    val color = listOf(
        colorResource(pokemonState.getPokemonColor() ?: R.color.white),
        colorResource(R.color.white)
    ).mix()

    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(colorResource(R.color.white), RoundedCornerShape(16.dp))
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        types.let { type -> // Todo: Consertar o let depois não é necessario
            if (types.isNotEmpty())
                LazyRow(content = {
                    itemsIndexed(type) { _, item ->
                        TypeComponent(item.typeState)
                    }
                })
        }

        Spacer(Modifier.height(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "About",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 24.sp,
            color = color
        )

        Spacer(Modifier.height(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description.replace("\n", " ")
                .replace("\u000c", " "),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp
        )

        Spacer(Modifier.height(16.dp))

        stats.let { stat ->

            if (stat.isNotEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Base Stats",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 24.sp,
                    color = color
                )

                Spacer(Modifier.height(16.dp))

                LazyColumn(content = {
                    itemsIndexed(stat) { _, item ->
                        StatsComponent(item, color)
                    }
                })
            }
        }
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    Content(PokemonDetailsUIState.Loading) {
        // Do nothing
    }
}

@Preview
@Composable
private fun ErrorPreview() {
    Content(PokemonDetailsUIState.Error("Error state")) {
        // Do nothing
    }
}

@Preview
@Composable
private fun SuccessStatePreview() {
    val mockPokemon = PokemonState(
        id = 25,
        name = "pikachu",
        height = 0.4,
        weight = 6.0,
        types = listOf(
            TypeSlotState(slot = 1, typeState = TypeState(name = "Electric"))
        ),
        sprites = SpritesState(
            default = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            shiny = ""
        ),
        stats = listOf(
            StatsState(value = 35f, stat = StatState(statName = "hp")),
            StatsState(value = 55f, stat = StatState(statName = "attack")),
            StatsState(value = 40f, stat = StatState(statName = "defense")),
            StatsState(value = 50f, stat = StatState(statName = "special-attack")),
            StatsState(value = 50f, stat = StatState(statName = "special-defense")),
            StatsState(value = 90f, stat = StatState(statName = "speed"))
        ),
        specieDetails = SpecieDetailsState(
            descriptions = listOf(
                PokemonDescriptionState(
                    text = "When several of these Pokémon gather, their electricity could build " +
                            "and cause lightning storms."
                )
            )
        )
    )

    Content(PokemonDetailsUIState.Success(mockPokemon)) {
        // Do nothing
    }
}